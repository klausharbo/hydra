package hydra.ext.scala.meta;

public abstract class Type_Ref {
  private Type_Ref () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Name instance) ;
    
    R visit(Select instance) ;
    
    R visit(Project instance) ;
    
    R visit(Singleton instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Type_Ref instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Name instance) {
      return otherwise((instance));
    }
    
    default R visit(Select instance) {
      return otherwise((instance));
    }
    
    default R visit(Project instance) {
      return otherwise((instance));
    }
    
    default R visit(Singleton instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Name extends hydra.ext.scala.meta.Type_Ref {
    public final hydra.ext.scala.meta.Type_Name value;
    
    public Name (hydra.ext.scala.meta.Type_Name value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Name)) {
        return false;
      }
      Name o = (Name) (other);
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
  
  public static final class Select extends hydra.ext.scala.meta.Type_Ref {
    public final hydra.ext.scala.meta.Type_Select value;
    
    public Select (hydra.ext.scala.meta.Type_Select value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Select)) {
        return false;
      }
      Select o = (Select) (other);
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
  
  public static final class Project extends hydra.ext.scala.meta.Type_Ref {
    public final hydra.ext.scala.meta.Type_Project value;
    
    public Project (hydra.ext.scala.meta.Type_Project value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Project)) {
        return false;
      }
      Project o = (Project) (other);
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
  
  public static final class Singleton extends hydra.ext.scala.meta.Type_Ref {
    public final hydra.ext.scala.meta.Type_Singleton value;
    
    public Singleton (hydra.ext.scala.meta.Type_Singleton value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Singleton)) {
        return false;
      }
      Singleton o = (Singleton) (other);
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