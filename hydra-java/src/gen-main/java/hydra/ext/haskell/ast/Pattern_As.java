package hydra.ext.haskell.ast;

public class Pattern_As {
  public final hydra.ext.haskell.ast.Name name;
  
  public final hydra.ext.haskell.ast.Pattern inner;
  
  public Pattern_As (hydra.ext.haskell.ast.Name name, hydra.ext.haskell.ast.Pattern inner) {
    this.name = name;
    this.inner = inner;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Pattern_As)) {
      return false;
    }
    Pattern_As o = (Pattern_As) (other);
    return name.equals(o.name) && inner.equals(o.inner);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode() + 3 * inner.hashCode();
  }
  
  public Pattern_As withName(hydra.ext.haskell.ast.Name name) {
    return new Pattern_As(name, inner);
  }
  
  public Pattern_As withInner(hydra.ext.haskell.ast.Pattern inner) {
    return new Pattern_As(name, inner);
  }
}