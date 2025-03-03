package hydra.ext.tinkerpop.v3;

/**
 * A key/value property
 */
public class Property<P> {
  public final hydra.ext.tinkerpop.v3.PropertyKey key;
  
  public final P value;
  
  public Property (hydra.ext.tinkerpop.v3.PropertyKey key, P value) {
    this.key = key;
    this.value = value;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Property)) {
      return false;
    }
    Property o = (Property) (other);
    return key.equals(o.key) && value.equals(o.value);
  }
  
  @Override
  public int hashCode() {
    return 2 * key.hashCode() + 3 * value.hashCode();
  }
  
  public Property withKey(hydra.ext.tinkerpop.v3.PropertyKey key) {
    return new Property(key, value);
  }
  
  public Property withValue(P value) {
    return new Property(key, value);
  }
}