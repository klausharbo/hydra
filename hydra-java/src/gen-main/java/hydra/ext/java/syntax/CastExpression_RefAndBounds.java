package hydra.ext.java.syntax;

public class CastExpression_RefAndBounds {
  public final hydra.ext.java.syntax.ReferenceType type;
  
  public final java.util.List<hydra.ext.java.syntax.AdditionalBound> bounds;
  
  public CastExpression_RefAndBounds (hydra.ext.java.syntax.ReferenceType type, java.util.List<hydra.ext.java.syntax.AdditionalBound> bounds) {
    this.type = type;
    this.bounds = bounds;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof CastExpression_RefAndBounds)) {
      return false;
    }
    CastExpression_RefAndBounds o = (CastExpression_RefAndBounds) (other);
    return type.equals(o.type) && bounds.equals(o.bounds);
  }
  
  @Override
  public int hashCode() {
    return 2 * type.hashCode() + 3 * bounds.hashCode();
  }
  
  public CastExpression_RefAndBounds withType(hydra.ext.java.syntax.ReferenceType type) {
    return new CastExpression_RefAndBounds(type, bounds);
  }
  
  public CastExpression_RefAndBounds withBounds(java.util.List<hydra.ext.java.syntax.AdditionalBound> bounds) {
    return new CastExpression_RefAndBounds(type, bounds);
  }
}