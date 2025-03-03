package hydra.ext.tinkerpop.typed;

/**
 * The type of a collection, such as a list of strings or an optional integer value
 */
public abstract class CollectionType {
  private CollectionType () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(List instance) ;
    
    R visit(Map instance) ;
    
    R visit(Optional instance) ;
    
    R visit(Set instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(CollectionType instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(List instance) {
      return otherwise((instance));
    }
    
    default R visit(Map instance) {
      return otherwise((instance));
    }
    
    default R visit(Optional instance) {
      return otherwise((instance));
    }
    
    default R visit(Set instance) {
      return otherwise((instance));
    }
  }
  
  public static final class List extends hydra.ext.tinkerpop.typed.CollectionType {
    public final hydra.ext.tinkerpop.typed.Type value;
    
    public List (hydra.ext.tinkerpop.typed.Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof List)) {
        return false;
      }
      List o = (List) (other);
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
  
  public static final class Map extends hydra.ext.tinkerpop.typed.CollectionType {
    public final hydra.ext.tinkerpop.typed.Type value;
    
    public Map (hydra.ext.tinkerpop.typed.Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Map)) {
        return false;
      }
      Map o = (Map) (other);
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
  
  public static final class Optional extends hydra.ext.tinkerpop.typed.CollectionType {
    public final hydra.ext.tinkerpop.typed.Type value;
    
    public Optional (hydra.ext.tinkerpop.typed.Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Optional)) {
        return false;
      }
      Optional o = (Optional) (other);
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
  
  public static final class Set extends hydra.ext.tinkerpop.typed.CollectionType {
    public final hydra.ext.tinkerpop.typed.Type value;
    
    public Set (hydra.ext.tinkerpop.typed.Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Set)) {
        return false;
      }
      Set o = (Set) (other);
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