module Hydra.Ext.Scala.Coder (printGraph) where

import Hydra.Core
import Hydra.Evaluation
import Hydra.Graph
import Hydra.Monads
import Hydra.Impl.Haskell.Dsl.Terms
import qualified Hydra.Impl.Haskell.Dsl.Types as Types
import Hydra.Lexical
import qualified Hydra.Ext.Scala.Meta as Scala
import qualified Hydra.Lib.Strings as Strings
import Hydra.Ext.Scala.Language
import Hydra.Ext.Scala.Utils
import Hydra.Adapters.Coders
import Hydra.Rewriting
import Hydra.Types.Inference
import Hydra.Types.Substitution
import Hydra.Util.Codetree.Script
import Hydra.Ext.Scala.Serde
import Hydra.Lexical
import Hydra.CoreDecoding

import qualified Control.Monad as CM
import qualified Data.List as L
import qualified Data.Map as M
import qualified Data.Set as S
import qualified Data.Maybe as Y


printGraph :: (Ord m, Read m, Show m) => Graph m -> GraphFlow m (M.Map FilePath String)
printGraph g = do
  pkg <- moduleToScalaPackage g
  let s = printExpr $ parenthesize $ writePkg pkg
  return $ M.fromList [(graphNameToFilePath False (FileExtension "scala") $ graphName g, s)]

moduleToScalaPackage :: (Ord m, Read m, Show m) => Graph m -> GraphFlow m Scala.Pkg
moduleToScalaPackage = graphToExternalModule language encodeUntypedTerm constructModule

constructModule :: (Ord m, Show m) => Graph m -> M.Map (Type m) (Coder (Context m) (Term m) Scala.Data) -> [(Element m, TypedTerm m)]
  -> GraphFlow m Scala.Pkg
constructModule g coders pairs = do
    defs <- CM.mapM toDef pairs
    let pname = toScalaName $ h $ graphName g
    let pref = Scala.Data_RefName pname
    return $ Scala.Pkg pname pref (imports ++ defs)
  where
    h (GraphName n) = n
    imports = (toElImport <$> S.toList (graphDependencies True False True g))
        ++ (toPrimImport <$> S.toList (graphDependencies False True False g))
      where
        toElImport (GraphName gname) = Scala.StatImportExport $ Scala.ImportExportStatImport $ Scala.Import [
          Scala.Importer (Scala.Data_RefName $ toScalaName gname) [Scala.ImporteeWildcard]]
        toPrimImport (GraphName gname) = Scala.StatImportExport $ Scala.ImportExportStatImport $ Scala.Import [
          Scala.Importer (Scala.Data_RefName $ toScalaName gname) []]
    toScalaName name = Scala.Data_Name $ Scala.PredefString $ L.intercalate "." $ Strings.splitOn "/" name
    toDef (el, TypedTerm typ term) = do
        let coder = Y.fromJust $ M.lookup typ coders
        rhs <- coderEncode coder term
        Scala.StatDefn <$> case rhs of
          Scala.DataApply _ -> toVal rhs
          Scala.DataFunctionData fun -> case stripType typ of
            TypeFunction (FunctionType _ cod) -> toDefn fun cod
            _ -> fail $ "expected function type, but found " ++ show typ
          Scala.DataLit _ -> toVal rhs
          Scala.DataRef _ -> toVal rhs -- TODO
          _ -> fail $ "unexpected RHS: " ++ show rhs
      where
        lname = localNameOf $ elementName el

        freeTypeVars = S.toList $ freeVariablesInType typ

        toDefn (Scala.Data_FunctionDataFunction (Scala.Data_Function params body)) cod = do
          let tparams = stparam <$> freeTypeVars
          scod <- encodeType cod
          return $ Scala.DefnDef $ Scala.Defn_Def []
            (Scala.Data_Name $ Scala.PredefString lname) tparams [params] (Just scod) body

        toVal rhs = pure $ Scala.DefnVal $ Scala.Defn_Val [] [namePat] Nothing rhs
          where
            namePat = Scala.PatVar $ Scala.Pat_Var $ Scala.Data_Name $ Scala.PredefString lname

encodeFunction :: (Eq m, Ord m, Read m, Show m) => m -> Function m -> Y.Maybe (Term m) -> GraphFlow m Scala.Data
encodeFunction meta fun arg = case fun of
    FunctionLambda (Lambda (Variable v) body) -> slambda v <$> encodeTerm body <*> findSdom
    FunctionPrimitive name -> pure $ sprim name
    FunctionElimination e -> case e of
      EliminationElement -> pure $ sname "DATA" -- TODO
      EliminationNominal name -> pure $ sname $ "ELIM-NOMINAL(" ++ show name ++ ")" -- TODO
      EliminationOptional c -> pure $ sname "ELIM-OPTIONAL" -- TODO
      EliminationRecord p -> fail "unapplied projection not yet supported"
      EliminationUnion (CaseStatement _ cases) -> do
          let v = "v"
          dom <- findDomain
          ftypes <- withSchemaContext $ fieldTypes dom
          cx <- getState
          let sn = nameOfType cx dom
          scases <- CM.mapM (encodeCase ftypes sn) cases
          case arg of
            Nothing -> slambda v <$> pure (Scala.DataMatch $ Scala.Data_Match (sname v) scases) <*> findSdom
            Just a -> do
              sa <- encodeTerm a
              return $ Scala.DataMatch $ Scala.Data_Match sa scases
        where
          encodeCase ftypes sn f@(Field fname fterm) = do
  --            dom <- findDomain (termMeta fterm)           -- Option #1: use type inference
              let dom = Y.fromJust $ M.lookup fname ftypes -- Option #2: look up the union type
              let patArgs = if dom == Types.unit then [] else [svar v]
              -- Note: PatExtract has the right syntax, though this may or may not be the Scalameta-intended way to use it
              let pat = Scala.PatExtract $ Scala.Pat_Extract (sname $ qualifyUnionFieldName "MATCHED." sn fname) patArgs
              body <- encodeTerm $ applyVar fterm v
              return $ Scala.Case pat Nothing body
            where
              v = Variable "y"
          applyVar fterm var@(Variable v) = case stripTerm fterm of
            TermFunction (FunctionLambda (Lambda v1 body)) -> if isFreeIn v1 body
              then body
              else substituteVariable v1 var body
            _ -> apply fterm (variable v)
    _ -> fail $ "unexpected function: " ++ show fun
  where
    findSdom = Just <$> (findDomain >>= encodeType)
    findDomain = do
        cx <- getState
        r <- annotationClassTypeOf (contextAnnotations cx) meta
        case r of
          Nothing -> fail "expected a typed term"
          Just t -> domainOf t
      where
        domainOf t = case stripType t of
          TypeFunction (FunctionType dom _) -> pure dom
          TypeElement et -> domainOf et
          _ -> fail $ "expected a function type, but found " ++ show t

encodeLiteral :: Literal -> GraphFlow m Scala.Lit
encodeLiteral av = case av of
    LiteralBoolean b -> pure $ Scala.LitBoolean b
    LiteralFloat fv -> case fv of
      FloatValueFloat32 f -> pure $ Scala.LitFloat f
      FloatValueFloat64 f -> pure $ Scala.LitDouble f
      _ -> unexpected "floating-point number" fv
    LiteralInteger iv -> case iv of
      IntegerValueInt16 i -> pure $ Scala.LitShort $ fromIntegral i
      IntegerValueInt32 i -> pure $ Scala.LitInt i
      IntegerValueInt64 i -> pure $ Scala.LitLong $ fromIntegral i
      IntegerValueUint8 i -> pure $ Scala.LitByte $ fromIntegral i
      _ -> unexpected "integer" iv
    LiteralString s -> pure $ Scala.LitString s
    _ -> unexpected "literal value" av

encodeTerm :: (Eq m, Ord m, Read m, Show m) => Term m -> GraphFlow m Scala.Data
encodeTerm term = case stripTerm term of
    TermApplication (Application fun arg) -> case stripTerm fun of
        TermFunction f -> case f of
          FunctionElimination e -> case e of
            EliminationElement -> encodeTerm arg
            EliminationNominal name -> fallback
            EliminationOptional c -> fallback
            EliminationRecord (Projection _ (FieldName fname)) -> do
              sarg <- encodeTerm arg
              return $ Scala.DataRef $ Scala.Data_RefSelect $ Scala.Data_Select sarg
                (Scala.Data_Name $ Scala.PredefString fname)
            EliminationUnion _ -> do
              cx <- getState
              encodeFunction (termMeta cx fun) f (Just arg)
          _ -> fallback
        _ -> fallback
      where
        fallback = sapply <$> encodeTerm fun <*> ((: []) <$> encodeTerm arg)
    TermElement name -> pure $ sname $ localNameOf name
    TermFunction f -> do
      cx <- getState
      encodeFunction (termMeta cx term) f Nothing
    TermList els -> sapply (sname "Seq") <$> CM.mapM encodeTerm els
    TermLiteral v -> Scala.DataLit <$> encodeLiteral v
    TermMap m -> sapply (sname "Map") <$> CM.mapM toPair (M.toList m)
      where
        toPair (k, v) = sassign <$> encodeTerm k <*> encodeTerm v
    TermNominal (Named _ term') -> encodeTerm term'
    TermOptional m -> case m of
      Nothing -> pure $ sname "None"
      Just t -> (\s -> sapply (sname "Some") [s]) <$> encodeTerm t
    TermRecord (Record n fields) -> do
      sn <- schemaName
      case sn of
        Nothing -> fail $ "unexpected anonymous record: " ++ show term
        Just name -> do
          let n = scalaTypeName False name
          args <- CM.mapM encodeTerm (fieldTerm <$> fields)
          return $ sapply (sname n) args
    TermSet s -> sapply (sname "Set") <$> CM.mapM encodeTerm (S.toList s)
    TermUnion (Union n (Field fn ft)) -> do
      sn <- schemaName
      let lhs = sname $ qualifyUnionFieldName "UNION." sn fn
      args <- case stripTerm ft of
        TermRecord (Record _ []) -> pure []
        _ -> do
          arg <- encodeTerm ft
          return [arg]
      return $ sapply lhs args
    TermVariable (Variable v) -> pure $ sname v
    _ -> fail $ "unexpected term: " ++ show term
  where
    schemaName = do
      cx <- getState
      r <- annotationClassTermType (contextAnnotations cx) term
      pure $ r >>= nameOfType cx

encodeType :: Show m => Type m -> GraphFlow m Scala.Type
encodeType t = case stripType t of
--  TypeElement et ->
  TypeFunction (FunctionType dom cod) -> do
    sdom <- encodeType dom
    scod <- encodeType cod
    return $ Scala.TypeFunctionType $ Scala.Type_FunctionTypeFunction $ Scala.Type_Function [sdom] scod
  TypeList lt -> stapply1 <$> pure (stref "Seq") <*> encodeType lt
  TypeLiteral lt -> case lt of
--    TypeBinary ->
    LiteralTypeBoolean -> pure $ stref "Boolean"
    LiteralTypeFloat ft -> case ft of
--      FloatTypeBigfloat ->
      FloatTypeFloat32 -> pure $ stref "Float"
      FloatTypeFloat64 -> pure $ stref "Double"
    LiteralTypeInteger it -> case it of
--      IntegerTypeBigint ->
--      IntegerTypeInt8 ->
      IntegerTypeInt16 -> pure $ stref "Short"
      IntegerTypeInt32 -> pure $ stref "Int"
      IntegerTypeInt64 -> pure $ stref "Long"
      IntegerTypeUint8 -> pure $ stref "Byte"
--      IntegerTypeUint16 ->
--      IntegerTypeUint32 ->
--      IntegerTypeUint64 ->
    LiteralTypeString -> pure $ stref "String"
  TypeMap (MapType kt vt) -> stapply2 <$> pure (stref "Map") <*> encodeType kt <*> encodeType vt
  TypeNominal name -> pure $ stref $ scalaTypeName True name
  TypeOptional ot -> stapply1 <$> pure (stref "Option") <*> encodeType ot
--  TypeRecord sfields ->
  TypeSet st -> stapply1 <$> pure (stref "Set") <*> encodeType st
--  TypeUnion sfields ->
  TypeLambda (LambdaType v body) -> do
    sbody <- encodeType body
    return $ Scala.TypeLambda $ Scala.Type_Lambda [stparam v] sbody
  TypeVariable (VariableType v) -> pure $ Scala.TypeVar $ Scala.Type_Var $ Scala.Type_Name v
  _ -> fail $ "can't encode unsupported type in Scala: " ++ show t

encodeUntypedTerm :: (Eq m, Ord m, Read m, Show m) => Term m -> GraphFlow m Scala.Data
encodeUntypedTerm term = do
    (term1, _) <- inferType term
    cx <- getState
    let annotType (m, typ, _) = annotationClassSetTypeOf (contextAnnotations cx) (Just typ) m
    encodeTerm $ rewriteTermMeta annotType term1
