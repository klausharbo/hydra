package hydra.ext.java.syntax;

public abstract class Statement {
  private Statement () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(WithoutTrailing instance) ;
    
    R visit(Labeled instance) ;
    
    R visit(IfThen instance) ;
    
    R visit(IfThenElse instance) ;
    
    R visit(While instance) ;
    
    R visit(For instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Statement instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(WithoutTrailing instance) {
      return otherwise((instance));
    }
    
    default R visit(Labeled instance) {
      return otherwise((instance));
    }
    
    default R visit(IfThen instance) {
      return otherwise((instance));
    }
    
    default R visit(IfThenElse instance) {
      return otherwise((instance));
    }
    
    default R visit(While instance) {
      return otherwise((instance));
    }
    
    default R visit(For instance) {
      return otherwise((instance));
    }
  }
  
  public static final class WithoutTrailing extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.StatementWithoutTrailingSubstatement value;
    
    public WithoutTrailing (hydra.ext.java.syntax.StatementWithoutTrailingSubstatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof WithoutTrailing)) {
        return false;
      }
      WithoutTrailing o = (WithoutTrailing) (other);
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
  
  public static final class Labeled extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.LabeledStatement value;
    
    public Labeled (hydra.ext.java.syntax.LabeledStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Labeled)) {
        return false;
      }
      Labeled o = (Labeled) (other);
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
  
  public static final class IfThen extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.IfThenStatement value;
    
    public IfThen (hydra.ext.java.syntax.IfThenStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof IfThen)) {
        return false;
      }
      IfThen o = (IfThen) (other);
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
  
  public static final class IfThenElse extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.IfThenElseStatement value;
    
    public IfThenElse (hydra.ext.java.syntax.IfThenElseStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof IfThenElse)) {
        return false;
      }
      IfThenElse o = (IfThenElse) (other);
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
  
  public static final class While extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.WhileStatement value;
    
    public While (hydra.ext.java.syntax.WhileStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof While)) {
        return false;
      }
      While o = (While) (other);
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
  
  public static final class For extends hydra.ext.java.syntax.Statement {
    public final hydra.ext.java.syntax.ForStatement value;
    
    public For (hydra.ext.java.syntax.ForStatement value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof For)) {
        return false;
      }
      For o = (For) (other);
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