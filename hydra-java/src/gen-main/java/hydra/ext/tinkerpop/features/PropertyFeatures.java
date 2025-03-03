package hydra.ext.tinkerpop.features;

/**
 * A base interface for Edge or Vertex Property features.
 */
public class PropertyFeatures {
  public final hydra.ext.tinkerpop.features.DataTypeFeatures dataTypeFeatures;
  
  /**
   * Determines if an Element allows for the processing of at least one data type defined by the features.
   */
  public final Boolean supportsProperties;
  
  public PropertyFeatures (hydra.ext.tinkerpop.features.DataTypeFeatures dataTypeFeatures, Boolean supportsProperties) {
    this.dataTypeFeatures = dataTypeFeatures;
    this.supportsProperties = supportsProperties;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof PropertyFeatures)) {
      return false;
    }
    PropertyFeatures o = (PropertyFeatures) (other);
    return dataTypeFeatures.equals(o.dataTypeFeatures) && supportsProperties.equals(o.supportsProperties);
  }
  
  @Override
  public int hashCode() {
    return 2 * dataTypeFeatures.hashCode() + 3 * supportsProperties.hashCode();
  }
  
  public PropertyFeatures withDataTypeFeatures(hydra.ext.tinkerpop.features.DataTypeFeatures dataTypeFeatures) {
    return new PropertyFeatures(dataTypeFeatures, supportsProperties);
  }
  
  public PropertyFeatures withSupportsProperties(Boolean supportsProperties) {
    return new PropertyFeatures(dataTypeFeatures, supportsProperties);
  }
}