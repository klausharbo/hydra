package hydra.ext.haskell.ast;

public class ValueBinding_Simple {
  public final hydra.ext.haskell.ast.Pattern pattern;
  
  public final hydra.ext.haskell.ast.RightHandSide rhs;
  
  public final java.util.Optional<hydra.ext.haskell.ast.LocalBindings> localBindings;
  
  public ValueBinding_Simple (hydra.ext.haskell.ast.Pattern pattern, hydra.ext.haskell.ast.RightHandSide rhs, java.util.Optional<hydra.ext.haskell.ast.LocalBindings> localBindings) {
    this.pattern = pattern;
    this.rhs = rhs;
    this.localBindings = localBindings;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ValueBinding_Simple)) {
      return false;
    }
    ValueBinding_Simple o = (ValueBinding_Simple) (other);
    return pattern.equals(o.pattern) && rhs.equals(o.rhs) && localBindings.equals(o.localBindings);
  }
  
  @Override
  public int hashCode() {
    return 2 * pattern.hashCode() + 3 * rhs.hashCode() + 5 * localBindings.hashCode();
  }
  
  public ValueBinding_Simple withPattern(hydra.ext.haskell.ast.Pattern pattern) {
    return new ValueBinding_Simple(pattern, rhs, localBindings);
  }
  
  public ValueBinding_Simple withRhs(hydra.ext.haskell.ast.RightHandSide rhs) {
    return new ValueBinding_Simple(pattern, rhs, localBindings);
  }
  
  public ValueBinding_Simple withLocalBindings(java.util.Optional<hydra.ext.haskell.ast.LocalBindings> localBindings) {
    return new ValueBinding_Simple(pattern, rhs, localBindings);
  }
}