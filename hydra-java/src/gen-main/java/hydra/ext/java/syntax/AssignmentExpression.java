package hydra.ext.java.syntax;

public abstract class AssignmentExpression {
  private AssignmentExpression () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Conditional instance) ;
    
    R visit(Assignment instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(AssignmentExpression instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Conditional instance) {
      return otherwise((instance));
    }
    
    default R visit(Assignment instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Conditional extends hydra.ext.java.syntax.AssignmentExpression {
    public final hydra.ext.java.syntax.ConditionalExpression value;
    
    public Conditional (hydra.ext.java.syntax.ConditionalExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Conditional)) {
        return false;
      }
      Conditional o = (Conditional) (other);
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
  
  public static final class Assignment extends hydra.ext.java.syntax.AssignmentExpression {
    public final hydra.ext.java.syntax.Assignment value;
    
    public Assignment (hydra.ext.java.syntax.Assignment value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Assignment)) {
        return false;
      }
      Assignment o = (Assignment) (other);
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