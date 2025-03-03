module Hydra.Impl.Haskell.Sources.Util.Codetree.Ast where

import Hydra.Impl.Haskell.Sources.Core

import Hydra.Core
import Hydra.Graph
import Hydra.Impl.Haskell.Dsl.Types as Types
import Hydra.Impl.Haskell.Dsl.Standard


codetreeAstModule :: Module Meta
codetreeAstModule = Module codetreeAst []

-- Note: here, the element namespace doubles as a graph name
codetreeAstName :: GraphName
codetreeAstName = GraphName "hydra/util/codetree/ast"

codetreeAst :: Graph Meta
codetreeAst = Graph codetreeAstName elements hydraCoreName
  where
    def = datatype codetreeAstName
    ast = nsref codetreeAstName

    elements = [

      def "Associativity" $
        doc "Operator associativity" $
        enum ["none", "left", "right", "both"],

      def "BlockStyle" $
        doc "Formatting option for code blocks" $
        record [
          "indent">: boolean,
          "newlineBeforeContent">: boolean,
          "newlineAfterContent">: boolean],
          
      def "BracketExpr" $
        doc "An expression enclosed by brackets" $
        record [
          "brackets">: ast "Brackets",
          "enclosed">: ast "Expr",
          "style">: ast "BlockStyle"],

      def "Brackets" $
        doc "Matching open and close bracket symbols" $
        record [
          "open">: ast "Symbol",
          "close">: ast "Symbol"],

      def "Expr" $
        doc "An abstract expression" $
        union [
          "const">: ast "Symbol",
          "op">: ast "OpExpr",
          "brackets">: ast "BracketExpr"],

      def "Op" $
        doc "An operator symbol" $
        record [
          "symbol">: ast "Symbol",
          "padding">: ast "Padding",
          "precedence">: ast "Precedence",
          "associativity">: ast "Associativity"],

      def "OpExpr" $
        doc "An operator expression" $
        record [
          "op">: ast "Op",
          "lhs">: ast "Expr",
          "rhs">: ast "Expr"],

      def "Padding" $
        doc "Left and right padding for an operator" $
        record [
          "left">: ast "Ws",
          "right">: ast "Ws"],

      def "Precedence" $
        doc "Operator precedence" $
        int32,

      def "Symbol" $
        doc "Any symbol"
        string,

      def "Ws" $
        doc "One of several classes of whitespace" $
        enum ["none", "space", "break", "breakAndIndent"]]
