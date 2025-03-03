package hydra.ext.java.syntax;

public abstract class BlockStatement {
  private BlockStatement () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(LocalVariableDeclaration instance) ;
    
    R visit(Class_ instance) ;
    
    R visit(Statement instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(BlockStatement instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(LocalVariableDeclaration instance) {
      return otherwise((instance));
    }
    
    default R visit(Class_ instance) {
      return otherwise((instance));
    }
    
    default R visit(Statement instance) {
      return otherwise((instance));
    }
  }
  
  public static final class LocalVariableDeclaration extends hydra.ext.java.syntax.BlockStatement {
    public final hydra.ext.java.syntax.LocalVariableDeclarationStatement value;
    
    public LocalVariableDeclaration (hydra.ext.java.syntax.LocalVariableDeclarationStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof LocalVariableDeclaration)) {
        return false;
      }
      LocalVariableDeclaration o = (LocalVariableDeclaration) (other);
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
  
  public static final class Class_ extends hydra.ext.java.syntax.BlockStatement {
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
  
  public static final class Statement extends hydra.ext.java.syntax.BlockStatement {
    public final hydra.ext.java.syntax.Statement value;
    
    public Statement (hydra.ext.java.syntax.Statement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Statement)) {
        return false;
      }
      Statement o = (Statement) (other);
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