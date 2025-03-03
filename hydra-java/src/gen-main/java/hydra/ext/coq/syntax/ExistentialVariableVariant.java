package hydra.ext.coq.syntax;

public abstract class ExistentialVariableVariant {
  private ExistentialVariableVariant () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Placeholder instance) ;
    
    R visit(Inside1 instance) ;
    
    R visit(Inside2 instance) ;
    
    R visit(Outside instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(ExistentialVariableVariant instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Placeholder instance) {
      return otherwise((instance));
    }
    
    default R visit(Inside1 instance) {
      return otherwise((instance));
    }
    
    default R visit(Inside2 instance) {
      return otherwise((instance));
    }
    
    default R visit(Outside instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Placeholder extends hydra.ext.coq.syntax.ExistentialVariableVariant {
    public Placeholder () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Placeholder)) {
        return false;
      }
      Placeholder o = (Placeholder) (other);
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
  
  public static final class Inside1 extends hydra.ext.coq.syntax.ExistentialVariableVariant {
    public Inside1 () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Inside1)) {
        return false;
      }
      Inside1 o = (Inside1) (other);
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
  
  public static final class Inside2 extends hydra.ext.coq.syntax.ExistentialVariableVariant {
    public Inside2 () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Inside2)) {
        return false;
      }
      Inside2 o = (Inside2) (other);
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
  
  public static final class Outside extends hydra.ext.coq.syntax.ExistentialVariableVariant {
    public final java.util.Optional<hydra.ext.coq.syntax.IdentArg> value;
    
    public Outside (java.util.Optional<hydra.ext.coq.syntax.IdentArg> value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Outside)) {
        return false;
      }
      Outside o = (Outside) (other);
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