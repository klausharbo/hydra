module Hydra.Impl.Haskell.Dsl (
  DataError,
  SchemaError,
  apply,
  bigfloatType,
  bigfloatValue,
  bigintType,
  bigintValue,
  binaryTerm,
  binaryType,
  booleanType,
  booleanValue,
  cases,
  compareTo,
  compose,
  constFunction,
  dataTerm,
  deref,
  elementRef,
  expectAtomicValue,
  expectNArgs,
  expectRecordTerm,
  expectStringTerm,
  expectUnionTerm,
  fieldsToMap,
  fieldTypesToMap,
  float32Type,
  float32Value,
  float64Type,
  float64Value,
  floatType,
  floatValue,
  function,
  functionType,
  int16Type,
  int16Value,
  int32Type,
  int32Value,
  int64Type,
  int64Value,
  int8Type,
  int8Value,
  integerType,
  integerValue,
  lambda,
  listType,
  mapType,
  match,
  matchWithVariants,
  nominalType,
  primitive,
  projection,
  requireField,
  stringTerm,
  stringType,
  stringValue,
  uint16Type,
  uint16Value,
  uint32Type,
  uint32Value,
  uint64Type,
  uint64Value,
  uint8Type,
  uint8Value,
  unitTerm,
  unitType,
  unitVariant,
  variable,
  variant,
  withFunction,
  withVariant,
) where

import Hydra.V1.Core
import Hydra.V1.Graph
import Hydra.Prototyping.Steps
import qualified Data.List as L
import qualified Data.Map as M
import qualified Data.Maybe as Y
import Data.Int


type DataError = String
type SchemaError = String


apply :: Term -> Term -> Term
apply func arg = TermApplication $ Application func arg

bigfloatType :: Type
bigfloatType = floatType FloatTypeBigfloat

bigfloatValue :: Double -> Term
bigfloatValue = floatValue . FloatValueBigfloat

bigintType :: Type
bigintType = integerType IntegerTypeBigint

bigintValue :: Integer -> Term
bigintValue = integerValue . IntegerValueBigint . fromIntegral

binaryTerm :: String -> Term
binaryTerm = TermAtomic . AtomicValueBinary

binaryType :: Type
binaryType = TypeAtomic AtomicTypeBinary

booleanType :: Type
booleanType = TypeAtomic AtomicTypeBoolean

booleanValue :: Bool -> Term
booleanValue b = TermAtomic $ AtomicValueBoolean $ if b then BooleanValueTrue else BooleanValueFalse

cases :: [Field] -> Term
cases = TermFunction . FunctionCases

compareTo :: Term -> Term
compareTo = TermFunction . FunctionCompareTo
 
compose :: Term -> Term -> Term
compose f2 f1 = lambda var $ apply f2 (apply f1 (variable var))
  where var = "x"

constFunction :: Term -> Term
constFunction = lambda "_"

dataTerm :: Term
dataTerm = TermFunction FunctionData

deref :: Name -> Term
deref name = apply dataTerm $ TermElement name

elementRef :: Element -> Term
elementRef el = apply dataTerm $ TermElement $ elementName el

expectAtomicValue :: Term -> Result AtomicValue
expectAtomicValue term = case term of
  TermAtomic av -> pure av
  _ -> fail $ "expected an atomic value, got " ++ show term

expectNArgs :: Int -> [Term] -> Result ()
expectNArgs n args = if L.length args /= n
  then fail $ "expected " ++ show n ++ " arguments, but found " ++ show (L.length args)
  else pure ()

expectRecordTerm :: Term -> Result [Field]
expectRecordTerm term = case term of
  TermRecord fields -> pure fields
  _ -> fail $ "expected a record, got " ++ show term

expectStringTerm :: Term -> Result String
expectStringTerm term = case term of
  TermAtomic (AtomicValueString s) -> pure s
  _ -> fail $ "expected a string, got " ++ show term

expectUnionTerm :: Term -> Result Field
expectUnionTerm term = case term of
  TermUnion field -> pure field
  _ -> fail $ "expected a union, got " ++ show term

fieldsToMap :: [Field] -> M.Map FieldName Term
fieldsToMap fields = M.fromList $ (\(Field name term) -> (name, term)) <$> fields

fieldTypesToMap :: [FieldType] -> M.Map FieldName Type
fieldTypesToMap fields = M.fromList $ (\(FieldType name typ) -> (name, typ)) <$> fields

float32Type :: Type
float32Type = floatType FloatTypeFloat32

float32Value :: Float -> Term
float32Value = floatValue . FloatValueFloat32

float64Type :: Type
float64Type = floatType FloatTypeFloat64

float64Value :: Double -> Term
float64Value = floatValue . FloatValueFloat64

floatType :: FloatType -> Type
floatType = TypeAtomic . AtomicTypeFloat

floatValue :: FloatValue -> Term
floatValue = TermAtomic . AtomicValueFloat

function :: Name -> Term
function = TermFunction . FunctionPrimitive

functionType :: Type -> Type -> Type
functionType dom cod = TypeFunction $ FunctionType dom cod

int16Type :: Type
int16Type = integerType IntegerTypeInt16

int16Value :: Int -> Term
int16Value = integerValue . IntegerValueInt16 . fromIntegral

int32Type :: Type
int32Type = integerType IntegerTypeInt32

int32Value :: Int -> Term
int32Value = integerValue . IntegerValueInt32

int64Type :: Type
int64Type = integerType IntegerTypeInt64

int64Value :: Int64 -> Term
int64Value = integerValue . IntegerValueInt64

int8Type :: Type
int8Type = integerType IntegerTypeInt8

int8Value :: Int -> Term
int8Value = integerValue . IntegerValueInt8 . fromIntegral

integerType :: IntegerType -> Type
integerType = TypeAtomic . AtomicTypeInteger

integerValue :: IntegerValue -> Term
integerValue = TermAtomic . AtomicValueInteger

lambda :: Variable -> Term -> Term
lambda param body = TermFunction $ FunctionLambda $ Lambda param body

listType :: Type -> Type
listType = TypeList

mapType :: Type -> Type -> Type
mapType kt vt = TypeMap $ MapType kt vt

match :: [(FieldName, Term)] -> Term
match = cases . fmap toField
  where
    toField (name, term) = Field name term

matchWithVariants :: [(FieldName, FieldName)] -> Term
matchWithVariants = cases . fmap toField
  where
    toField (from, to) = Field from $ constFunction $ unitVariant to

nominalType :: Name -> Type
nominalType = TypeNominal

primitive :: Name -> Term
primitive = TermFunction . FunctionPrimitive

projection :: Name -> Term
projection = TermFunction . FunctionProjection

requireField :: M.Map FieldName Term -> FieldName -> Result Term
requireField fields fname = Y.maybe error ResultSuccess $ M.lookup fname fields
  where
    error = fail $ "no such field: " ++ fname

stringTerm :: String -> Term
stringTerm = TermAtomic . AtomicValueString

stringType :: Type
stringType = TypeAtomic AtomicTypeString

stringValue :: String -> Term
stringValue = TermAtomic . AtomicValueString

uint16Type :: Type
uint16Type = integerType IntegerTypeUint16

uint16Value :: Integer -> Term
uint16Value = integerValue . IntegerValueUint16 . fromIntegral

uint32Type :: Type
uint32Type = integerType IntegerTypeUint32

uint32Value :: Integer -> Term
uint32Value = integerValue . IntegerValueUint32 . fromIntegral

uint64Type :: Type
uint64Type = integerType IntegerTypeUint64

uint64Value :: Integer -> Term
uint64Value = integerValue . IntegerValueUint64 . fromIntegral

uint8Type :: Type
uint8Type = integerType IntegerTypeUint8

uint8Value :: Integer -> Term
uint8Value = integerValue . IntegerValueUint8 . fromIntegral

unitTerm :: Term
unitTerm = TermRecord []

unitType :: Type
unitType = TypeRecord []

unitVariant :: FieldName -> Term
unitVariant fname = variant fname unitTerm

variable :: Variable -> Term
variable = TermVariable

variant :: FieldName -> Term -> Term
variant fname term = TermUnion (Field fname term)

withFunction :: FieldName -> Element -> Term
withFunction name el = lambda var $ variant name $ apply (elementRef el) (variable var)
  where var = "x"

withVariant :: FieldName -> Term
withVariant name = constFunction $ unitVariant name
