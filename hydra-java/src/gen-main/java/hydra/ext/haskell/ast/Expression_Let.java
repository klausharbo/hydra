package hydra.ext.haskell.ast;

/**
 * A 'let' expression
 */
public class Expression_Let {
  public final java.util.List<hydra.ext.haskell.ast.Pattern> bindings;
  
  public final hydra.ext.haskell.ast.Expression inner;
  
  public Expression_Let (java.util.List<hydra.ext.haskell.ast.Pattern> bindings, hydra.ext.haskell.ast.Expression inner) {
    this.bindings = bindings;
    this.inner = inner;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Expression_Let)) {
      return false;
    }
    Expression_Let o = (Expression_Let) (other);
    return bindings.equals(o.bindings) && inner.equals(o.inner);
  }
  
  @Override
  public int hashCode() {
    return 2 * bindings.hashCode() + 3 * inner.hashCode();
  }
  
  public Expression_Let withBindings(java.util.List<hydra.ext.haskell.ast.Pattern> bindings) {
    return new Expression_Let(bindings, inner);
  }
  
  public Expression_Let withInner(hydra.ext.haskell.ast.Expression inner) {
    return new Expression_Let(bindings, inner);
  }
}