package hydra.ext.java.syntax;

public abstract class ConditionalExpression {
  private ConditionalExpression () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Simple instance) ;
    
    R visit(TernaryCond instance) ;
    
    R visit(TernaryLambda instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(ConditionalExpression instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Simple instance) {
      return otherwise((instance));
    }
    
    default R visit(TernaryCond instance) {
      return otherwise((instance));
    }
    
    default R visit(TernaryLambda instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Simple extends hydra.ext.java.syntax.ConditionalExpression {
    public final hydra.ext.java.syntax.ConditionalOrExpression value;
    
    public Simple (hydra.ext.java.syntax.ConditionalOrExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Simple)) {
        return false;
      }
      Simple o = (Simple) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class TernaryCond extends hydra.ext.java.syntax.ConditionalExpression {
    public final hydra.ext.java.syntax.ConditionalExpression_TernaryCond value;
    
    public TernaryCond (hydra.ext.java.syntax.ConditionalExpression_TernaryCond value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof TernaryCond)) {
        return false;
      }
      TernaryCond o = (TernaryCond) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class TernaryLambda extends hydra.ext.java.syntax.ConditionalExpression {
    public final hydra.ext.java.syntax.ConditionalExpression_TernaryLambda value;
    
    public TernaryLambda (hydra.ext.java.syntax.ConditionalExpression_TernaryLambda value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof TernaryLambda)) {
        return false;
      }
      TernaryLambda o = (TernaryLambda) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
}