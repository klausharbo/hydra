module Hydra.Evaluation where

import qualified Hydra.Core as Core
import qualified Hydra.Graph as Graph
import Data.Map
import Data.Set

-- A typeclass-like construct providing common functions for working with annotations
data AnnotationClass m 
  = AnnotationClass {
    annotationClassDefault :: m,
    annotationClassEqual :: (m -> m -> Bool),
    annotationClassCompare :: (m -> m -> Core.Comparison),
    annotationClassShow :: (m -> String),
    annotationClassRead :: (String -> Maybe m),
    annotationClassTermMeta :: (Core.Term m -> m),
    annotationClassTypeMeta :: (Core.Type m -> m),
    annotationClassTermDescription :: (Core.Term m -> Flow (Context m) (Maybe String)),
    annotationClassTypeDescription :: (Core.Type m -> Flow (Context m) (Maybe String)),
    annotationClassTermType :: (Core.Term m -> Flow (Context m) (Maybe (Core.Type m))),
    annotationClassSetTermDescription :: (Context m -> Maybe String -> Core.Term m -> Core.Term m),
    annotationClassSetTermType :: (Context m -> Maybe (Core.Type m) -> Core.Term m -> Core.Term m),
    annotationClassTypeOf :: (m -> Flow (Context m) (Maybe (Core.Type m))),
    annotationClassSetTypeOf :: (Maybe (Core.Type m) -> m -> m)}

_AnnotationClass = (Core.Name "hydra/evaluation.AnnotationClass")

_AnnotationClass_default = (Core.FieldName "default")

_AnnotationClass_equal = (Core.FieldName "equal")

_AnnotationClass_compare = (Core.FieldName "compare")

_AnnotationClass_show = (Core.FieldName "show")

_AnnotationClass_read = (Core.FieldName "read")

_AnnotationClass_termMeta = (Core.FieldName "termMeta")

_AnnotationClass_typeMeta = (Core.FieldName "typeMeta")

_AnnotationClass_termDescription = (Core.FieldName "termDescription")

_AnnotationClass_typeDescription = (Core.FieldName "typeDescription")

_AnnotationClass_termType = (Core.FieldName "termType")

_AnnotationClass_setTermDescription = (Core.FieldName "setTermDescription")

_AnnotationClass_setTermType = (Core.FieldName "setTermType")

_AnnotationClass_typeOf = (Core.FieldName "typeOf")

_AnnotationClass_setTypeOf = (Core.FieldName "setTypeOf")

-- An encoder and decoder; a qualified bidirectional transformation between instances of two types
data Coder s a b 
  = Coder {
    coderEncode :: (a -> Flow s b),
    coderDecode :: (b -> Flow s a)}

_Coder = (Core.Name "hydra/evaluation.Coder")

_Coder_encode = (Core.FieldName "encode")

_Coder_decode = (Core.FieldName "decode")

-- Indicates either the 'out' or the 'in' direction of a coder
data CoderDirection 
  = CoderDirectionEncode 
  | CoderDirectionDecode 
  deriving (Eq, Ord, Read, Show)

_CoderDirection = (Core.Name "hydra/evaluation.CoderDirection")

_CoderDirection_encode = (Core.FieldName "encode")

_CoderDirection_decode = (Core.FieldName "decode")

-- A pointed set of graph modules; a graph in the logical sense
data Context m 
  = Context {
    contextGraphs :: (Graph.GraphSet m),
    contextElements :: (Map Core.Name (Graph.Element m)),
    contextFunctions :: (Map Core.Name (PrimitiveFunction m)),
    contextStrategy :: EvaluationStrategy,
    contextAnnotations :: (AnnotationClass m),
    contextTrace :: [String]}

_Context = (Core.Name "hydra/evaluation.Context")

_Context_graphs = (Core.FieldName "graphs")

_Context_elements = (Core.FieldName "elements")

_Context_functions = (Core.FieldName "functions")

_Context_strategy = (Core.FieldName "strategy")

_Context_annotations = (Core.FieldName "annotations")

_Context_trace = (Core.FieldName "trace")

-- Settings which determine how terms are evaluated
data EvaluationStrategy 
  = EvaluationStrategy {
    evaluationStrategyOpaqueTermVariants :: (Set Core.TermVariant)}
  deriving (Eq, Ord, Read, Show)

_EvaluationStrategy = (Core.Name "hydra/evaluation.EvaluationStrategy")

_EvaluationStrategy_opaqueTermVariants = (Core.FieldName "opaqueTermVariants")

-- A variant of the State monad with built-in logging and error handling
newtype Flow s a 
  = Flow {
    unFlow :: (s -> Trace -> FlowWrapper s a)}

_Flow = (Core.Name "hydra/evaluation.Flow")

data FlowWrapper s a 
  = FlowWrapper {
    flowWrapperValue :: (Maybe a),
    flowWrapperState :: s,
    flowWrapperTrace :: Trace}
  deriving (Eq, Ord, Read, Show)

_FlowWrapper = (Core.Name "hydra/evaluation.FlowWrapper")

_FlowWrapper_value = (Core.FieldName "value")

_FlowWrapper_state = (Core.FieldName "state")

_FlowWrapper_trace = (Core.FieldName "trace")

-- A built-in function
data PrimitiveFunction m 
  = PrimitiveFunction {
    primitiveFunctionName :: Core.Name,
    primitiveFunctionType :: (Core.FunctionType m),
    primitiveFunctionImplementation :: ([Core.Term m] -> Flow (Context m) (Core.Term m))}

_PrimitiveFunction = (Core.Name "hydra/evaluation.PrimitiveFunction")

_PrimitiveFunction_name = (Core.FieldName "name")

_PrimitiveFunction_type = (Core.FieldName "type")

_PrimitiveFunction_implementation = (Core.FieldName "implementation")

-- A type together with a coder for mapping terms into arguments for primitive functions, and mapping computed results into terms
data TermCoder m a 
  = TermCoder {
    termCoderType :: (Core.Type m),
    termCoderCoder :: (Coder (Context m) (Core.Term m) a)}

_TermCoder = (Core.Name "hydra/evaluation.TermCoder")

_TermCoder_type = (Core.FieldName "type")

_TermCoder_coder = (Core.FieldName "coder")

-- A container for logging and error information
data Trace 
  = Trace {
    traceStack :: [String],
    traceMessages :: [[String]]}
  deriving (Eq, Ord, Read, Show)

_Trace = (Core.Name "hydra/evaluation.Trace")

_Trace_stack = (Core.FieldName "stack")

_Trace_messages = (Core.FieldName "messages")