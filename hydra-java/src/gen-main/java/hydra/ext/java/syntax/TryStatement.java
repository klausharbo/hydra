package hydra.ext.java.syntax;

public abstract class TryStatement {
  private TryStatement () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Simple instance) ;
    
    R visit(WithFinally instance) ;
    
    R visit(WithResources instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(TryStatement instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Simple instance) {
      return otherwise((instance));
    }
    
    default R visit(WithFinally instance) {
      return otherwise((instance));
    }
    
    default R visit(WithResources instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Simple extends hydra.ext.java.syntax.TryStatement {
    public final hydra.ext.java.syntax.TryStatement_Simple value;
    
    public Simple (hydra.ext.java.syntax.TryStatement_Simple value) {
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
  
  public static final class WithFinally extends hydra.ext.java.syntax.TryStatement {
    public final hydra.ext.java.syntax.TryStatement_WithFinally value;
    
    public WithFinally (hydra.ext.java.syntax.TryStatement_WithFinally value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof WithFinally)) {
        return false;
      }
      WithFinally o = (WithFinally) (other);
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
  
  public static final class WithResources extends hydra.ext.java.syntax.TryStatement {
    public final hydra.ext.java.syntax.TryWithResourcesStatement value;
    
    public WithResources (hydra.ext.java.syntax.TryWithResourcesStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof WithResources)) {
        return false;
      }
      WithResources o = (WithResources) (other);
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