module Hydra.Impl.Haskell.Ext.Json.Serde where

import Hydra.Core
import Hydra.Evaluation
import Hydra.Ext.Json.Coder
import Hydra.Monads
import qualified Hydra.Ext.Json.Model as Json

import qualified Data.ByteString.Lazy as BS
import qualified Control.Monad as CM
import qualified Data.Aeson as A
import qualified Data.List as L
import qualified Data.Map as M
import qualified Data.Text as T
import qualified Data.ByteString.Lazy.Char8 as L8
import qualified Data.Vector as V
import qualified Data.HashMap.Strict as HS
import qualified Data.Scientific as SC
import qualified Data.Char as C
import qualified Data.String as String


aesonToBytes :: A.Value -> BS.ByteString
aesonToBytes = A.encode

aesonToValue :: A.Value -> Json.Value
aesonToValue v = case v of
  A.Object o -> Json.ValueObject $ M.fromList (mapPair <$> HS.toList o)
    where
      mapPair (k, v) = (T.unpack k, aesonToValue v)
  A.Array a -> Json.ValueArray (aesonToValue <$> V.toList a)
  A.String t -> Json.ValueString $ T.unpack t
  A.Number s -> Json.ValueNumber $ SC.toRealFloat s
  A.Bool b -> Json.ValueBoolean b
  A.Null -> Json.ValueNull

bytesToAeson :: BS.ByteString -> Maybe A.Value
bytesToAeson = A.decode

bytesToString :: BS.ByteString -> String
bytesToString = map (C.chr . fromEnum) . BS.unpack

bytesToValue :: BS.ByteString -> Maybe Json.Value
bytesToValue bs = aesonToValue <$> bytesToAeson bs

jsonSerde :: (Eq m, Ord m, Read m, Show m) => Type m -> GraphFlow m (Coder (Context m) (Term m) BS.ByteString)
jsonSerde typ = do
  coder <- jsonCoder typ
  return Coder {
    coderEncode = fmap valueToBytes . coderEncode coder,
    coderDecode = \bs -> case bytesToValue bs of
        Nothing -> fail "JSON parsing failed"
        Just v -> coderDecode coder v}

jsonSerdeStr :: (Eq m, Ord m, Read m, Show m) => Type m -> GraphFlow m (Coder (Context m) (Term m) String)
jsonSerdeStr typ = do
  serde <- jsonSerde typ
  return Coder {
    coderEncode = fmap L8.unpack . coderEncode serde,
    coderDecode = coderDecode serde . L8.pack}

stringToBytes :: String -> BS.ByteString
stringToBytes = String.fromString

stringToValue :: String -> Maybe Json.Value
stringToValue = bytesToValue . stringToBytes

valueToAeson :: Json.Value -> A.Value
valueToAeson v = case v of
    Json.ValueArray l -> A.Array $ V.fromList (valueToAeson <$> l)
    Json.ValueBoolean b -> A.Bool b
    Json.ValueNull -> A.Null
    Json.ValueNumber d -> A.Number $ SC.fromFloatDigits d
    Json.ValueObject m -> A.Object $ HS.fromList (mapPair <$> M.toList m)
      where
        mapPair (k, v) = (T.pack k, valueToAeson v)
    Json.ValueString s -> A.String $ T.pack s

valueToBytes :: Json.Value -> BS.ByteString
valueToBytes = aesonToBytes . valueToAeson

valueToString :: Json.Value -> String
valueToString = bytesToString . valueToBytes
