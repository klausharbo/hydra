package hydra.ext.java.syntax;

public abstract class VariableInitializer {
  private VariableInitializer () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Expression instance) ;
    
    R visit(ArrayInitializer instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(VariableInitializer instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Expression instance) {
      return otherwise((instance));
    }
    
    default R visit(ArrayInitializer instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Expression extends hydra.ext.java.syntax.VariableInitializer {
    public final hydra.ext.java.syntax.Expression value;
    
    public Expression (hydra.ext.java.syntax.Expression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Expression)) {
        return false;
      }
      Expression o = (Expression) (other);
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
  
  public static final class ArrayInitializer extends hydra.ext.java.syntax.VariableInitializer {
    public final hydra.ext.java.syntax.ArrayInitializer value;
    
    public ArrayInitializer (hydra.ext.java.syntax.ArrayInitializer value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof ArrayInitializer)) {
        return false;
      }
      ArrayInitializer o = (ArrayInitializer) (other);
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