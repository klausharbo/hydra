module Hydra.Types.Unification (
  Constraint,
  Subst,
  TypeError(..),
  failWithTypeError,
  solveConstraints,
  typeErrorMessage,
  unify,
) where

import Hydra.Common
import Hydra.Core
import Hydra.Evaluation
import Hydra.Monads
import Hydra.Types.Substitution
import Hydra.Impl.Haskell.Dsl.Types as Types
import Hydra.Graph

import qualified Data.Map as M
import qualified Data.Set as S


type Constraint m = (Type m, Type m)

data TypeError m
  = UnificationFail (Type m) (Type m)
  | InfiniteType VariableType (Type m)
  | UnificationMismatch [Type m] [Type m]
  | ElementUndefined Name
  | InvalidTypeEncoding String deriving (Eq, Show)

failWithTypeError :: Show m => TypeError m -> GraphFlow m x
failWithTypeError = fail . typeErrorMessage

typeErrorMessage :: Show m => TypeError m -> String
typeErrorMessage e = "type error: " ++ show e

type Unifier m = (Subst m, [Constraint m])


bind :: (Eq m, Show m) => VariableType -> Type m -> GraphFlow m (Subst m)
bind a t | t == TypeVariable a = return M.empty
         | variableOccursInType a t = failWithTypeError $ InfiniteType a t
         | otherwise = return $ M.singleton a t

solveConstraints :: (Eq m, Show m) => [Constraint m] -> GraphFlow m (Subst m)
solveConstraints cs = unificationSolver (M.empty, cs)

unificationSolver :: (Eq m, Show m) => Unifier m -> GraphFlow m (Subst m)
unificationSolver (su, cs) = case cs of
  [] -> return su
  ((t1, t2): cs0) -> do
    su1  <- unify t1 t2
    unificationSolver (
      composeSubst su1 su,
      (\(t1, t2) -> (substituteInType su1 t1, substituteInType su1 t2)) <$> cs0)

unify :: (Eq m, Show m) => Type m -> Type m -> GraphFlow m (Subst m)
unify t1 t2 = if t1 == t2
    then return M.empty
    else case (t1, t2) of
      (TypeAnnotated (Annotated at _), _) -> unify at t2
      (_, TypeAnnotated (Annotated at _)) -> unify t1 at
      (TypeElement et1, TypeElement et2) -> unify et1 et2
      (TypeFunction (FunctionType dom cod), TypeFunction (FunctionType t3 t4)) -> unifyMany [dom, cod] [t3, t4]
      (TypeList lt1, TypeList lt2) -> unify lt1 lt2
      (TypeMap (MapType k1 v1), TypeMap (MapType k2 v2)) -> unifyMany [k1, v1] [k2, v2]
      (TypeOptional ot1, TypeOptional ot2) -> unify ot1 ot2
      (TypeRecord rt1, TypeRecord rt2) -> verify (rowTypeTypeName rt1 == rowTypeTypeName rt2)
      (TypeSet st1, TypeSet st2) -> unify st1 st2
      (TypeUnion rt1, TypeUnion rt2) -> verify (rowTypeTypeName rt1 == rowTypeTypeName rt2)
      (TypeLambda (LambdaType (VariableType v1) body1), TypeLambda (LambdaType (VariableType v2) body2)) -> unifyMany
        [Types.variable v1, body1] [Types.variable v2, body2]
      (TypeVariable v, _) -> bind v t2
      (_, TypeVariable v) -> bind v t1
      (TypeNominal n1, TypeNominal n2) -> if n1 == n2
        then return M.empty
        else failUnification
      (TypeNominal _, _) -> return M.empty -- TODO
      (_, TypeNominal name) -> unify (Types.nominal name) t1
      _ -> failUnification
  where
    verify b = if b then return M.empty else failUnification
    failUnification = failWithTypeError $ UnificationFail t1 t2
    
unifyMany :: (Eq m, Show m) => [Type m] -> [Type m] -> GraphFlow m (Subst m)
unifyMany [] [] = return M.empty
unifyMany (t1 : ts1) (t2 : ts2) =
  do su1 <- unify t1 t2
     su2 <- unifyMany (substituteInType su1 <$> ts1) (substituteInType su1 <$> ts2)
     return (composeSubst su2 su1)
unifyMany t1 t2 = failWithTypeError $ UnificationMismatch t1 t2

variableOccursInType ::  Show m => VariableType -> Type m -> Bool
variableOccursInType a t = S.member a $ freeVariablesInType t
