package hydra.ext.scala.meta;

public abstract class Decl {
  private Decl () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Val instance) ;
    
    R visit(Var instance) ;
    
    R visit(Def instance) ;
    
    R visit(Type instance) ;
    
    R visit(Given instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Decl instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Val instance) {
      return otherwise((instance));
    }
    
    default R visit(Var instance) {
      return otherwise((instance));
    }
    
    default R visit(Def instance) {
      return otherwise((instance));
    }
    
    default R visit(Type instance) {
      return otherwise((instance));
    }
    
    default R visit(Given instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Val extends hydra.ext.scala.meta.Decl {
    public final hydra.ext.scala.meta.Decl_Val value;
    
    public Val (hydra.ext.scala.meta.Decl_Val value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Val)) {
        return false;
      }
      Val o = (Val) (other);
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
  
  public static final class Var extends hydra.ext.scala.meta.Decl {
    public final hydra.ext.scala.meta.Decl_Var value;
    
    public Var (hydra.ext.scala.meta.Decl_Var value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Var)) {
        return false;
      }
      Var o = (Var) (other);
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
  
  public static final class Def extends hydra.ext.scala.meta.Decl {
    public final hydra.ext.scala.meta.Decl_Def value;
    
    public Def (hydra.ext.scala.meta.Decl_Def value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Def)) {
        return false;
      }
      Def o = (Def) (other);
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
  
  public static final class Type extends hydra.ext.scala.meta.Decl {
    public final hydra.ext.scala.meta.Decl_Type value;
    
    public Type (hydra.ext.scala.meta.Decl_Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Type)) {
        return false;
      }
      Type o = (Type) (other);
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
  
  public static final class Given extends hydra.ext.scala.meta.Decl {
    public final hydra.ext.scala.meta.Decl_Given value;
    
    public Given (hydra.ext.scala.meta.Decl_Given value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Given)) {
        return false;
      }
      Given o = (Given) (other);
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