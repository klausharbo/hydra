package hydra.util.codetree.ast;

/**
 * One of several classes of whitespace
 */
public abstract class Ws {
  private Ws () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(None instance) ;
    
    R visit(Space instance) ;
    
    R visit(Break instance) ;
    
    R visit(BreakAndIndent instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Ws instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(None instance) {
      return otherwise((instance));
    }
    
    default R visit(Space instance) {
      return otherwise((instance));
    }
    
    default R visit(Break instance) {
      return otherwise((instance));
    }
    
    default R visit(BreakAndIndent instance) {
      return otherwise((instance));
    }
  }
  
  public static final class None extends hydra.util.codetree.ast.Ws {
    public None () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof None)) {
        return false;
      }
      None o = (None) (other);
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
  
  public static final class Space extends hydra.util.codetree.ast.Ws {
    public Space () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Space)) {
        return false;
      }
      Space o = (Space) (other);
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
  
  public static final class Break extends hydra.util.codetree.ast.Ws {
    public Break () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Break)) {
        return false;
      }
      Break o = (Break) (other);
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
  
  public static final class BreakAndIndent extends hydra.util.codetree.ast.Ws {
    public BreakAndIndent () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof BreakAndIndent)) {
        return false;
      }
      BreakAndIndent o = (BreakAndIndent) (other);
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