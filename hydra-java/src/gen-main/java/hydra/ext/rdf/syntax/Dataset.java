package hydra.ext.rdf.syntax;

public class Dataset {
  public final java.util.Set<hydra.ext.rdf.syntax.Quad> value;
  
  public Dataset (java.util.Set<hydra.ext.rdf.syntax.Quad> value) {
    this.value = value;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Dataset)) {
      return false;
    }
    Dataset o = (Dataset) (other);
    return value.equals(o.value);
  }
  
  @Override
  public int hashCode() {
    return 2 * value.hashCode();
  }
}