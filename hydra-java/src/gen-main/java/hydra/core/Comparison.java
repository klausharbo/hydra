package hydra.core;

/**
 * An equality judgement: less than, equal to, or greater than
 */
public abstract class Comparison {
  private Comparison () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(LessThan instance) ;
    
    R visit(EqualTo instance) ;
    
    R visit(GreaterThan instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Comparison instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(LessThan instance) {
      return otherwise((instance));
    }
    
    default R visit(EqualTo instance) {
      return otherwise((instance));
    }
    
    default R visit(GreaterThan instance) {
      return otherwise((instance));
    }
  }
  
  public static final class LessThan extends hydra.core.Comparison {
    public LessThan () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof LessThan)) {
        return false;
      }
      LessThan o = (LessThan) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class EqualTo extends hydra.core.Comparison {
    public EqualTo () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof EqualTo)) {
        return false;
      }
      EqualTo o = (EqualTo) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class GreaterThan extends hydra.core.Comparison {
    public GreaterThan () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof GreaterThan)) {
        return false;
      }
      GreaterThan o = (GreaterThan) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
}