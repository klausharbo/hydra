package hydra.ext.java.syntax;

public class MultiplicativeExpression_Binary {
  public final hydra.ext.java.syntax.MultiplicativeExpression lhs;
  
  public final hydra.ext.java.syntax.UnaryExpression rhs;
  
  public MultiplicativeExpression_Binary (hydra.ext.java.syntax.MultiplicativeExpression lhs, hydra.ext.java.syntax.UnaryExpression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof MultiplicativeExpression_Binary)) {
      return false;
    }
    MultiplicativeExpression_Binary o = (MultiplicativeExpression_Binary) (other);
    return lhs.equals(o.lhs) && rhs.equals(o.rhs);
  }
  
  @Override
  public int hashCode() {
    return 2 * lhs.hashCode() + 3 * rhs.hashCode();
  }
  
  public MultiplicativeExpression_Binary withLhs(hydra.ext.java.syntax.MultiplicativeExpression lhs) {
    return new MultiplicativeExpression_Binary(lhs, rhs);
  }
  
  public MultiplicativeExpression_Binary withRhs(hydra.ext.java.syntax.UnaryExpression rhs) {
    return new MultiplicativeExpression_Binary(lhs, rhs);
  }
}