package hydra.ext.scala.meta;

public class Data_For {
  public final java.util.List<hydra.ext.scala.meta.Enumerator> enums;
  
  public Data_For (java.util.List<hydra.ext.scala.meta.Enumerator> enums) {
    this.enums = enums;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Data_For)) {
      return false;
    }
    Data_For o = (Data_For) (other);
    return enums.equals(o.enums);
  }
  
  @Override
  public int hashCode() {
    return 2 * enums.hashCode();
  }
}