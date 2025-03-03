module Hydra.Types.Inference (
  inferType,
  Constraint,
) where

import Hydra.Core
import Hydra.Evaluation
import Hydra.Graph
import Hydra.Basics
import Hydra.Lexical
import Hydra.CoreDecoding
import qualified Hydra.Impl.Haskell.Dsl.Types as Types
import Hydra.Monads
import Hydra.Types.Substitution
import Hydra.Types.Unification
import Hydra.Rewriting
import Hydra.Reduction
import Hydra.Lexical

import qualified Control.Monad as CM
import qualified Data.List as L
import qualified Data.Map as M
import qualified Data.Set as S


type InferenceContext m = (Context m, Int, TypingEnvironment m)

type TypingEnvironment m = M.Map Variable (TypeScheme m)

-- Decode a type, eliminating nominal types for the sake of unification
decodeStructuralType :: Show m => Term m -> GraphFlow m (Type m)
decodeStructuralType term = do
  typ <- decodeType term
  let typ' = stripType typ
  case typ' of
    TypeNominal name -> withSchemaContext $ do
        el <- requireElement (Just "decode structural type") name
        decodeStructuralType $ elementData el
    _ -> pure typ

freshVariableType :: Flow (InferenceContext m) (Type m)
freshVariableType = do
    (cx, s, e) <- getState
    putState (cx, s + 1, e)
    return $ Types.variable (unVariableType $ normalVariables !! s)

generalize :: Show m => TypingEnvironment m -> Type m -> TypeScheme m
generalize env t  = TypeScheme vars t
  where
    vars = S.toList $ S.difference
      (freeVariablesInType t)
      (L.foldr (S.union . freeVariablesInScheme) S.empty $ M.elems env)

extendEnvironment :: Variable -> TypeScheme m -> Flow (InferenceContext m) a -> Flow (InferenceContext m) a
extendEnvironment x sc = withEnvironment (\e -> M.insert x sc $ M.delete x e)

findMatchingField :: Show m => FieldName -> [FieldType m] -> Flow (InferenceContext m) (FieldType m)
findMatchingField fname sfields = case L.filter (\f -> fieldTypeName f == fname) sfields of
  []    -> fail $ "no such field: " ++ unFieldName fname
  (h:_) -> return h

infer :: (Ord m, Show m) => Term m -> Flow (InferenceContext m) (Term (m, Type m, [Constraint m]))
infer term = do
  (cx, _, _) <- getState
  mt <- withGraphContext $ annotationClassTermType (contextAnnotations cx) term
  case mt of
    Just typ -> do
      i <- inferInternal term
      return $ TermAnnotated $ Annotated i (termMeta cx term, typ, []) -- TODO: unify "suggested" types with inferred types
    Nothing -> inferInternal term

inferInternal :: (Ord m, Show m) => Term m -> Flow (InferenceContext m) (Term (m, Type m, [Constraint m]))
inferInternal term = case stripTerm term of
    TermApplication (Application fun arg) -> do
      ifun <- infer fun
      iarg <- infer arg
      v <- freshVariableType
      let c = (termConstraints ifun) ++ (termConstraints iarg) ++ [(termType ifun, Types.function (termType iarg) v)]
      let app = TermApplication $ Application ifun iarg
      yield app v c

    TermElement name -> do
      et <- withGraphContext $ typeOfElement name
      yield (TermElement name) (Types.element et) []

    TermFunction f -> case f of

      -- Note: here we assume that compareTo evaluates to an integer, not a Comparison value.
      --       For the latter, Comparison would have to be added to the literal type grammar.
      FunctionCompareTo other -> do
        i <- infer other
        yieldFunction (FunctionCompareTo i) (Types.function (termType i) Types.int8) (termConstraints i)

      FunctionElimination e -> case e of

        EliminationElement -> do
          et <- freshVariableType
          yieldElimination EliminationElement (Types.function (Types.element et) et) []

        EliminationNominal name -> do
          typ <- withGraphContext $ namedType "eliminate nominal" name
          yieldElimination (EliminationNominal name) (Types.function (Types.nominal name) typ) []

        EliminationOptional (OptionalCases n j) -> do
          dom <- freshVariableType
          cod <- freshVariableType
          ni <- infer n
          ji <- infer j
          let t = Types.function (Types.optional dom) cod
          let constraints = [(cod, termType ni), (Types.function dom cod, termType ji)]
          yieldElimination (EliminationOptional $ OptionalCases ni ji) t constraints

        -- Note: type inference cannot recover complete record types from projections; type annotations are needed
        EliminationRecord (Projection name fname) -> do
          rt <- withGraphContext $ requireRecordType name
          sfield <- findMatchingField fname (rowTypeFields rt)
          yieldElimination (EliminationRecord $ Projection name fname)
            (Types.function (TypeRecord rt) $ fieldTypeType sfield) []

        EliminationUnion (CaseStatement name cases) -> do
            rt <- withGraphContext $ requireUnionType name
            let sfields = rowTypeFields rt

            icases <- CM.mapM inferFieldType cases
            let innerConstraints = L.concat (termConstraints . fieldTerm <$> icases)

            let idoms = termType . fieldTerm <$> icases
            let sdoms = fieldTypeType <$> sfields
            cod <- freshVariableType
            let outerConstraints = L.zipWith (\t d -> (t, Types.function d cod)) idoms sdoms

            yieldElimination (EliminationUnion (CaseStatement name  icases))
              (Types.function (TypeUnion rt) cod)
              (innerConstraints ++ outerConstraints)

      FunctionLambda (Lambda v body) -> do
        tv <- freshVariableType
        i <- extendEnvironment v (TypeScheme [] tv) $ infer body
        yieldFunction (FunctionLambda $ Lambda v i) (Types.function tv (termType i)) (termConstraints i)

      FunctionPrimitive name -> do
        FunctionType dom cod <- withGraphContext $ typeOfPrimitiveFunction name
        yieldFunction (FunctionPrimitive name) (Types.function dom cod) []

    TermLet (Let x e1 e2) -> do
      (_, _, env) <- getState
      i1 <- infer e1
      let t1 = termType i1
      let c1 = termConstraints i1
      sub <- withGraphContext $ solveConstraints c1
      let t1' = reduceType $ substituteInType sub t1
      let sc = generalize (M.map (substituteInScheme sub) env) t1'
      i2 <- extendEnvironment x sc $ withEnvironment (M.map (substituteInScheme sub)) $ infer e2
      let t2 = termType i2
      let c2 = termConstraints i2
      yield (TermLet $ Let x i1 i2) t2 (c1 ++ c2) -- TODO: is x constant?

    TermList els -> do
      v <- freshVariableType
      iels <- CM.mapM infer els
      let co = (\e -> (v, termType e)) <$> iels
      let ci = L.concat (termConstraints <$> iels)
      yield (TermList iels) (Types.list v) (co ++ ci)

    TermLiteral l -> yield (TermLiteral l) (Types.literal $ literalType l) []

    TermMap m -> do
        kv <- freshVariableType
        vv <- freshVariableType
        pairs <- CM.mapM toPair $ M.toList m
        let co = L.concat ((\(k, v) -> [(kv, termType k), (vv, termType v)]) <$> pairs)
        let ci = L.concat ((\(k, v) -> termConstraints k ++ termConstraints v) <$> pairs)
        yield (TermMap $ M.fromList pairs) (Types.map kv vv) (co ++ ci)
      where
        toPair (k, v) = do
          ik <- infer k
          iv <- infer v
          return (ik, iv)

    TermNominal (Named name term1) -> do
      typ <- withGraphContext $ namedType "nominal" name
      i <- infer term1
      let typ1 = termType i
      let c = termConstraints i
      yield (TermNominal $ Named name i) (Types.nominal name) (c ++ [(typ, typ1)])

    TermOptional m -> do
      v <- freshVariableType
      case m of
        Nothing -> yield (TermOptional Nothing) (Types.optional v) []
        Just e -> do
          i <- infer e
          yield (TermOptional $ Just i) (Types.optional v) ((v, termType i):(termConstraints i))

    TermRecord (Record n fields) -> do
        rt <- withGraphContext $ requireRecordType n
        let sfields = rowTypeFields rt
        (fields0, ftypes0, c1) <- CM.foldM forField ([], [], []) $ L.zip fields sfields
        yield (TermRecord $ Record n $ L.reverse fields0) (TypeRecord $ RowType n $ L.reverse ftypes0) c1
      where
        forField (typed, ftypes, c) (field, sfield) = do
          i <- inferFieldType field
          let ft = termType $ fieldTerm i
          let cinternal = termConstraints $ fieldTerm i
          let cnominal = (ft, fieldTypeType sfield)
          return (i:typed, (FieldType (fieldName field) ft):ftypes, cnominal:(cinternal ++ c))

    TermSet els -> do
      v <- freshVariableType
      iels <- CM.mapM infer $ S.toList els
      let co = (\e -> (v, termType e)) <$> iels
      let ci = L.concat (termConstraints <$> iels)
      yield (TermSet $ S.fromList iels) (Types.set v) (co ++ ci)

    -- Note: type inference cannot recover complete union types from union values; type annotations are needed
    TermUnion (Union n field) -> do
        rt <- withGraphContext $ requireUnionType n
        sfield <- findMatchingField (fieldName field) (rowTypeFields rt)
        ifield <- inferFieldType field
        let cinternal = termConstraints $ fieldTerm ifield
        let cnominal = (termType $ fieldTerm ifield, fieldTypeType sfield)
        let constraints = cnominal:cinternal
        yield (TermUnion $ Union n ifield) (TypeUnion rt) constraints

    TermVariable x -> do
      t <- lookupTypeInEnvironment x
      yield (TermVariable x) t []
  where
    yieldFunction fun = yield (TermFunction fun)

    yieldElimination e = yield (TermFunction $ FunctionElimination e)

    yield term typ constraints = case term of
      TermAnnotated (Annotated term' (meta, _, _)) -> return $ TermAnnotated $ Annotated term' (meta, typ, constraints)
      _ -> do
        (cx, _, _) <- getState
        return $ TermAnnotated $ Annotated term (annotationClassDefault $ contextAnnotations cx, typ, constraints)

inferFieldType :: (Ord m, Show m) => Field m -> Flow (InferenceContext m) (Field (m, Type m, [Constraint m]))
inferFieldType (Field fname term) = Field fname <$> infer term

-- | Solve for the toplevel type of an expression in a given environment
inferType :: (Ord m, Show m) => Term m -> GraphFlow m (Term (m, Type m, [Constraint m]), TypeScheme m)
inferType term = do
    cx <- getState
    withState (startContext cx) $ do
      term1 <- infer term
      subst <- withGraphContext $ withSchemaContext $ solveConstraints (termConstraints term1)
      let term2 = rewriteDataType (substituteInType subst) term1
      let ts = closeOver $ termType term2
      return (term2, ts)
  where
    -- | Canonicalize and return the polymorphic toplevel type.
    closeOver = normalizeScheme . generalize M.empty . reduceType

instantiate :: TypeScheme m -> Flow (InferenceContext m) (Type m)

instantiate (TypeScheme vars t) = do
    vars1 <- mapM (const freshVariableType) vars
    return $ substituteInType (M.fromList $ zip vars vars1) t

lookupTypeInEnvironment :: Show m => Variable -> Flow (InferenceContext m) (Type m)
lookupTypeInEnvironment v = do
  (_, _, env) <- getState
  case M.lookup v env of
    Nothing -> fail $ "unbound variable: " ++ unVariable v
    Just s  -> instantiate s

namedType :: Show m => String -> Name -> GraphFlow m (Type m)
namedType debug name = do
  el <- requireElement (Just debug) name
  withSchemaContext $ decodeStructuralType $ elementData el

reduceType :: (Ord m, Show m) => Type m -> Type m
reduceType t = t -- betaReduceType cx t

rewriteDataType :: Ord m => (Type m -> Type m) -> Term (m, Type m, [Constraint m]) -> Term (m, Type m, [Constraint m])
rewriteDataType f = rewriteTermMeta rewrite
  where
    rewrite (x, typ, c) = (x, f typ, c)

startContext :: Context m -> InferenceContext m
startContext cx = (cx, 0, M.empty)

termConstraints :: Term (m, Type m, [Constraint m]) -> [Constraint m]
termConstraints (TermAnnotated (Annotated _ (_, _, constraints))) = constraints

termType :: Term (m, Type m, [Constraint m]) -> Type m
termType (TermAnnotated (Annotated _ (_, typ, _))) = typ

typeOfElement :: Show m => Name -> GraphFlow m (Type m)
typeOfElement name = do
  el <- requireElement (Just "type of element") name
  decodeStructuralType $ elementSchema el

typeOfPrimitiveFunction :: Name -> GraphFlow m (FunctionType m)
typeOfPrimitiveFunction name = primitiveFunctionType <$> requirePrimitiveFunction name

withEnvironment :: (TypingEnvironment m -> TypingEnvironment m) -> Flow (InferenceContext m) a -> Flow (InferenceContext m) a
withEnvironment m f = do
  (cx, i, e) <- getState
  withState (cx, i, m e) f

withGraphContext :: GraphFlow m a -> Flow (InferenceContext m) a
withGraphContext f = do
  (cx, _, _) <- getState
  withState cx f
