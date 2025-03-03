package hydra.ext.java.syntax;

public abstract class StatementExpression {
  private StatementExpression () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Assignment instance) ;
    
    R visit(PreIncrement instance) ;
    
    R visit(PreDecrement instance) ;
    
    R visit(PostIncrement instance) ;
    
    R visit(PostDecrement instance) ;
    
    R visit(MethodInvocation instance) ;
    
    R visit(ClassInstanceCreation instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(StatementExpression instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Assignment instance) {
      return otherwise((instance));
    }
    
    default R visit(PreIncrement instance) {
      return otherwise((instance));
    }
    
    default R visit(PreDecrement instance) {
      return otherwise((instance));
    }
    
    default R visit(PostIncrement instance) {
      return otherwise((instance));
    }
    
    default R visit(PostDecrement instance) {
      return otherwise((instance));
    }
    
    default R visit(MethodInvocation instance) {
      return otherwise((instance));
    }
    
    default R visit(ClassInstanceCreation instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Assignment extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.Assignment value;
    
    public Assignment (hydra.ext.java.syntax.Assignment value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Assignment)) {
        return false;
      }
      Assignment o = (Assignment) (other);
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
  
  public static final class PreIncrement extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.PreIncrementExpression value;
    
    public PreIncrement (hydra.ext.java.syntax.PreIncrementExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof PreIncrement)) {
        return false;
      }
      PreIncrement o = (PreIncrement) (other);
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
  
  public static final class PreDecrement extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.PreDecrementExpression value;
    
    public PreDecrement (hydra.ext.java.syntax.PreDecrementExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof PreDecrement)) {
        return false;
      }
      PreDecrement o = (PreDecrement) (other);
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
  
  public static final class PostIncrement extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.PostIncrementExpression value;
    
    public PostIncrement (hydra.ext.java.syntax.PostIncrementExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof PostIncrement)) {
        return false;
      }
      PostIncrement o = (PostIncrement) (other);
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
  
  public static final class PostDecrement extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.PostDecrementExpression value;
    
    public PostDecrement (hydra.ext.java.syntax.PostDecrementExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof PostDecrement)) {
        return false;
      }
      PostDecrement o = (PostDecrement) (other);
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
  
  public static final class MethodInvocation extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.MethodInvocation value;
    
    public MethodInvocation (hydra.ext.java.syntax.MethodInvocation value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof MethodInvocation)) {
        return false;
      }
      MethodInvocation o = (MethodInvocation) (other);
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
  
  public static final class ClassInstanceCreation extends hydra.ext.java.syntax.StatementExpression {
    public final hydra.ext.java.syntax.ClassInstanceCreationExpression value;
    
    public ClassInstanceCreation (hydra.ext.java.syntax.ClassInstanceCreationExpression value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof ClassInstanceCreation)) {
        return false;
      }
      ClassInstanceCreation o = (ClassInstanceCreation) (other);
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