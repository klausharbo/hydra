package hydra.ext.rdf.syntax;

public abstract class Resource {
  private Resource () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Iri instance) ;
    
    R visit(Bnode instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Resource instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Iri instance) {
      return otherwise((instance));
    }
    
    default R visit(Bnode instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Iri extends hydra.ext.rdf.syntax.Resource {
    public final hydra.ext.rdf.syntax.Iri value;
    
    public Iri (hydra.ext.rdf.syntax.Iri value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Iri)) {
        return false;
      }
      Iri o = (Iri) (other);
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
  
  public static final class Bnode extends hydra.ext.rdf.syntax.Resource {
    public final hydra.ext.rdf.syntax.BlankNode value;
    
    public Bnode (hydra.ext.rdf.syntax.BlankNode value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Bnode)) {
        return false;
      }
      Bnode o = (Bnode) (other);
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