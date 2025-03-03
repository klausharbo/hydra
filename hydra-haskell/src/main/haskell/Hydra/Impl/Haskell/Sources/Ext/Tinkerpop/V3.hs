{-# LANGUAGE OverloadedStrings #-}

module Hydra.Impl.Haskell.Sources.Ext.Tinkerpop.V3 where

import Hydra.Impl.Haskell.Sources.Core

import Hydra.Core
import Hydra.Graph
import Hydra.Impl.Haskell.Dsl.Types as Types
import Hydra.Impl.Haskell.Dsl.Standard


tinkerpopV3Module :: Module Meta
tinkerpopV3Module = Module tinkerpopV3 [hydraCoreModule]

tinkerpopV3Name :: GraphName
tinkerpopV3Name = GraphName "hydra/ext/tinkerpop/v3"

tinkerpopV3 :: Graph Meta
tinkerpopV3 = Graph tinkerpopV3Name elements hydraCoreName
  where
    core = nsref hydraCoreName
    v3 = nsref tinkerpopV3Name
    def = datatype tinkerpopV3Name
    elements = [

      def "Edge" $
        doc "An edge" $
        lambda "v" $ lambda "e" $ lambda "p" $
        record [
          "id">: "e",
          "out">: "v",
          "in">: "v",
          "properties">: Types.map (v3 "PropertyKey") "p"],

      def "Element" $
        doc "Either a vertex or an edge" $
        lambda "v" $ lambda "e" $ lambda "p" $
        union [
          "vertex">: v3 "Vertex" @@ "v" @@ "p",
          "edge">: v3 "Edge" @@ "v" @@ "e" @@ "p"],

      def "Graph" $
        doc "A graph; a self-contained collection of vertices and edges" $
        lambda "v" $ lambda "e" $ lambda "p" $
        record [
          "vertices">: Types.set $ v3 "Vertex" @@ "v" @@ "p",
          "edges">: Types.set $ v3 "Edge" @@ "v" @@ "e" @@ "p"],

      def "Property" $
        doc "A key/value property" $
        lambda "p" $
        record [
          "key">: v3 "PropertyKey",
          "value">: "p"],

      def "PropertyKey" $
        doc "A property key"
        string,

      def "Vertex" $
        doc "A vertex" $
        lambda "v" $ lambda "p" $
        record [
          "id">: "v",
          "properties">: Types.map (v3 "PropertyKey") "p"]]
