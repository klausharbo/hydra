package hydra.ext.scala.meta;

public abstract class Data_FunctionData {
  private Data_FunctionData () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(ContextFunction instance) ;
    
    R visit(Function instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Data_FunctionData instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(ContextFunction instance) {
      return otherwise((instance));
    }
    
    default R visit(Function instance) {
      return otherwise((instance));
    }
  }
  
  public static final class ContextFunction extends hydra.ext.scala.meta.Data_FunctionData {
    public final hydra.ext.scala.meta.Data_ContextFunction value;
    
    public ContextFunction (hydra.ext.scala.meta.Data_ContextFunction value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof ContextFunction)) {
        return false;
      }
      ContextFunction o = (ContextFunction) (other);
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
  
  public static final class Function extends hydra.ext.scala.meta.Data_FunctionData {
    public final hydra.ext.scala.meta.Data_Function value;
    
    public Function (hydra.ext.scala.meta.Data_Function value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Function)) {
        return false;
      }
      Function o = (Function) (other);
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