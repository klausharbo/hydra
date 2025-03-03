package hydra.ext.scala.meta;

public class Import {
  public final java.util.List<hydra.ext.scala.meta.Importer> importers;
  
  public Import (java.util.List<hydra.ext.scala.meta.Importer> importers) {
    this.importers = importers;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Import)) {
      return false;
    }
    Import o = (Import) (other);
    return importers.equals(o.importers);
  }
  
  @Override
  public int hashCode() {
    return 2 * importers.hashCode();
  }
}