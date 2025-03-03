package hydra.ext.java.syntax;

public abstract class InterfaceMemberDeclaration {
  private InterfaceMemberDeclaration () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Constant instance) ;
    
    R visit(InterfaceMethod instance) ;
    
    R visit(Class_ instance) ;
    
    R visit(Interface instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(InterfaceMemberDeclaration instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Constant instance) {
      return otherwise((instance));
    }
    
    default R visit(InterfaceMethod instance) {
      return otherwise((instance));
    }
    
    default R visit(Class_ instance) {
      return otherwise((instance));
    }
    
    default R visit(Interface instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Constant extends hydra.ext.java.syntax.InterfaceMemberDeclaration {
    public final hydra.ext.java.syntax.ConstantDeclaration value;
    
    public Constant (hydra.ext.java.syntax.ConstantDeclaration value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Constant)) {
        return false;
      }
      Constant o = (Constant) (other);
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
  
  public static final class InterfaceMethod extends hydra.ext.java.syntax.InterfaceMemberDeclaration {
    public final hydra.ext.java.syntax.InterfaceMethodDeclaration value;
    
    public InterfaceMethod (hydra.ext.java.syntax.InterfaceMethodDeclaration value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof InterfaceMethod)) {
        return false;
      }
      InterfaceMethod o = (InterfaceMethod) (other);
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
  
  public static final class Class_ extends hydra.ext.java.syntax.InterfaceMemberDeclaration {
    public final hydra.ext.java.syntax.ClassDeclaration value;
    
    public Class_ (hydra.ext.java.syntax.ClassDeclaration value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Class_)) {
        return false;
      }
      Class_ o = (Class_) (other);
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
  
  public static final class Interface extends hydra.ext.java.syntax.InterfaceMemberDeclaration {
    public final hydra.ext.java.syntax.InterfaceDeclaration value;
    
    public Interface (hydra.ext.java.syntax.InterfaceDeclaration value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Interface)) {
        return false;
      }
      Interface o = (Interface) (other);
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