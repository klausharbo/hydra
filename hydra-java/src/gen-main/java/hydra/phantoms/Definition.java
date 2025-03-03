package hydra.phantoms;

public class Definition<A> {
  public final hydra.core.Name name;
  
  public final hydra.phantoms.Datum<A> datum;
  
  public Definition (hydra.core.Name name, hydra.phantoms.Datum<A> datum) {
    this.name = name;
    this.datum = datum;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Definition)) {
      return false;
    }
    Definition o = (Definition) (other);
    return name.equals(o.name) && datum.equals(o.datum);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode() + 3 * datum.hashCode();
  }
  
  public Definition withName(hydra.core.Name name) {
    return new Definition(name, datum);
  }
  
  public Definition withDatum(hydra.phantoms.Datum<A> datum) {
    return new Definition(name, datum);
  }
}