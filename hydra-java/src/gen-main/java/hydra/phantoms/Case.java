package hydra.phantoms;

public class Case<A> {
  public final hydra.core.FieldName value;
  
  public Case (hydra.core.FieldName value) {
    this.value = value;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Case)) {
      return false;
    }
    Case o = (Case) (other);
    return value.equals(o.value);
  }
  
  @Override
  public int hashCode() {
    return 2 * value.hashCode();
  }
}