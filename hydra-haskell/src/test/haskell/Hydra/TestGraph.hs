module Hydra.TestGraph (
  graphElementsMap,
  testContext,
  testElementArthur,
  testGraph,
  testStrategy,
  testTermArthur,
  testTypePerson,
  testTypeTimestamp,
  module Hydra.Impl.Haskell.Sources.Libraries,
) where

import Hydra.Core
import Hydra.Graph
import Hydra.Evaluation
import Hydra.Impl.Haskell.Dsl.CoreMeta
import Hydra.Impl.Haskell.Dsl.Standard
import Hydra.Impl.Haskell.Sources.CoreGraph
import Hydra.Prototyping.Primitives
import Hydra.Impl.Haskell.Sources.Libraries

import qualified Data.Map  as M
import qualified Data.Set  as S


cx :: Context Meta
cx = standardContext

testContext :: Context Meta
testContext = standardContext {
    contextGraphs = GraphSet {
      graphSetGraphs = M.fromList [
        ("testGraph", testGraph),
        ("testSchemaGraph", testSchemaGraph),
        ("hydra/core", hydraCoreGraph)],
      graphSetRoot = "testGraph"},
    contextElements = graphElementsMap testGraph,
    contextFunctions = M.fromList $ fmap (\p -> (primitiveFunctionName p, p)) standardPrimitives,
    contextStrategy = EvaluationStrategy {
      evaluationStrategyOpaqueTermVariants = S.fromList [ -- TODO: revisit this list
        TermVariantLiteral,
        TermVariantElement,
        TermVariantFunction]}}

testElementArthur :: Element Meta
testElementArthur = Element {
  elementName = "ArthurDent",
  elementSchema = element "Person",
  elementData = testTermArthur}

testGraph :: Graph Meta
testGraph = Graph "testGraph" [testElementArthur] allTerms "testSchemaGraph"

testSchemaGraph :: Graph Meta
testSchemaGraph = Graph "testSchemaGraph" [
    typeElement cx "StringTypeAlias" stringType,
    typeElement cx "Person" testTypePerson,
    typeElement cx "Timestamp" testTypeTimestamp]
  allTerms "hydra/core"

testStrategy :: EvaluationStrategy
testStrategy = contextStrategy testContext

testTermArthur :: Term Meta
testTermArthur = nominalRecord cx "Person" [
  Field "firstName" $ stringValue "Arthur",
  Field "lastName" $ stringValue "Dent",
  Field "age" $ int32Value 42]

testTypePerson :: Type
testTypePerson = TypeRecord [
  FieldType "firstName" stringType,
  FieldType "lastName" stringType,
  FieldType "age" int32Type]

testTypeTimestamp :: Type
testTypeTimestamp = TypeUnion [
  FieldType "unixTimeMillis" uint64Type,
  FieldType "date" stringType]

allTerms :: Term Meta -> Bool
allTerms _ = True
