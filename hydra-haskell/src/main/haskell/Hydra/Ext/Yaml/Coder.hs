module Hydra.Ext.Yaml.Coder (yamlCoder) where

import Hydra.Core
import Hydra.Evaluation
import Hydra.Adapter
import Hydra.Adapters.Term
import Hydra.CoreLanguage
import Hydra.Impl.Haskell.Extras
import Hydra.Steps
import qualified Hydra.Impl.Haskell.Dsl.Terms as Terms
import Hydra.Ext.Yaml.Language
import qualified Hydra.Ext.Yaml.Model as YM

import qualified Control.Monad as CM
import qualified Data.Map as M
import qualified Data.Maybe as Y


literalCoder :: LiteralType -> Qualified (Step Literal YM.Scalar)
literalCoder at = pure $ case at of
  LiteralTypeBoolean -> Step {
    stepOut = \(LiteralBoolean b) -> pure $ YM.ScalarBool b,
    stepIn = \s -> case s of
      YM.ScalarBool b -> pure $ LiteralBoolean b
      _ -> unexpected "boolean" s}
  LiteralTypeFloat _ -> Step {
    stepOut = \(LiteralFloat (FloatValueBigfloat f)) -> pure $ YM.ScalarFloat f,
    stepIn = \s -> case s of
      YM.ScalarFloat f -> pure $ LiteralFloat $ FloatValueBigfloat f
      _ -> unexpected "floating-point value" s}
  LiteralTypeInteger _ -> Step {
    stepOut = \(LiteralInteger (IntegerValueBigint i)) -> pure $ YM.ScalarInt i,
    stepIn = \s -> case s of
      YM.ScalarInt i -> pure $ LiteralInteger $ IntegerValueBigint i
      _ -> unexpected "integer" s}
  LiteralTypeString -> Step {
    stepOut = \(LiteralString s) -> pure $ YM.ScalarStr s,
    stepIn = \s -> case s of
      YM.ScalarStr s' -> pure $ LiteralString s'
      _ -> unexpected "string" s}

recordCoder :: (Default m, Eq m, Ord m, Read m, Show m) => [FieldType m] -> Qualified (Step (Term m) YM.Node)
recordCoder sfields = do
    coders <- CM.mapM (\f -> (,) <$> pure f <*> termCoder (fieldTypeType f)) sfields
    return $ Step (encode coders) (decode coders)
  where
    encode coders term = case termExpr term of
      TermRecord fields -> YM.NodeMapping . M.fromList . Y.catMaybes <$> CM.zipWithM encodeField coders fields
        where
          encodeField (ft, coder) (Field (FieldName fn) fv) = case (fieldTypeType ft, fv) of
            (TypeOptional _, TermOptional Nothing) -> pure Nothing
            _ -> Just <$> ((,) <$> pure (yamlString fn) <*> stepOut coder fv)
      _ -> unexpected "record" term
    decode coders n = case n of
      YM.NodeMapping m -> Terms.record <$> CM.mapM (decodeField m) coders -- Note: unknown fields are ignored
        where
          decodeField m (FieldType fname@(FieldName fn) ft, coder) = do
            v <- stepIn coder $ Y.fromMaybe yamlNull $ M.lookup (yamlString fn) m
            return $ Field fname v
      _ -> unexpected "mapping" n
    getCoder coders fname = Y.maybe error pure $ M.lookup fname coders
      where
        error = fail $ "no such field: " ++ fname

termCoder :: (Default m, Eq m, Ord m, Read m, Show m) => Type m -> Qualified (Step (Term m) YM.Node)
termCoder typ = case typeExpr typ of
  TypeLiteral at -> do
    ac <- literalCoder at
    return Step {
      stepOut = \t -> case t of
         TermLiteral av -> YM.NodeScalar <$> stepOut ac av
         _ -> unexpected "literal" t,
      stepIn = \n -> case n of
        YM.NodeScalar s -> Terms.literal <$> stepIn ac s
        _ -> unexpected "scalar node" n}
  TypeList lt -> do
    lc <- termCoder lt
    return Step {
--      stepOut = \(Term (TermList els) _) -> YM.NodeSequence <$> CM.mapM (stepOut lc) els,
      stepOut = \t -> case t of
         TermList els -> YM.NodeSequence <$> CM.mapM (stepOut lc) els
         _ -> unexpected "list" t,
      stepIn = \n -> case n of
        YM.NodeSequence nodes -> Terms.list <$> CM.mapM (stepIn lc) nodes
        _ -> unexpected "sequence" n}
  TypeOptional ot -> do
    oc <- termCoder ot
    return Step {
      stepOut = \t -> case t of
         TermOptional el -> Y.maybe (pure yamlNull) (stepOut oc) el
         _ -> unexpected "optional" t,
      stepIn = \n -> case n of
        YM.NodeScalar YM.ScalarNull -> pure $ Terms.optional Nothing
        _ -> Terms.optional . Just <$> stepIn oc n}
  TypeMap (MapType kt vt) -> do
    kc <- termCoder kt
    vc <- termCoder vt
    let encodeEntry (k, v) = (,) <$> stepOut kc k <*> stepOut vc v
    let decodeEntry (k, v) = (,) <$> stepIn kc k <*> stepIn vc v
    return Step {
      stepOut = \t -> case t of
        TermMap m -> YM.NodeMapping . M.fromList <$> CM.mapM encodeEntry (M.toList m)
        _ -> unexpected "term" t,
      stepIn = \n -> case n of
        YM.NodeMapping m -> Terms.map . M.fromList <$> CM.mapM decodeEntry (M.toList m)
        _ -> unexpected "mapping" n}
  TypeRecord sfields -> recordCoder sfields

yamlCoder :: (Default m, Eq m, Ord m, Read m, Show m) => Context m -> Type m -> Qualified (Step (Term m) YM.Node)
yamlCoder context typ = do
    adapter <- termAdapter adContext typ
    coder <- termCoder $ adapterTarget adapter
    return $ composeSteps (adapterStep adapter) coder
  where
    adContext = AdapterContext context hydraCoreLanguage language

yamlNull :: YM.Node
yamlNull = YM.NodeScalar YM.ScalarNull

yamlString :: String -> YM.Node
yamlString = YM.NodeScalar . YM.ScalarStr
