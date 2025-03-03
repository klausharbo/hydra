package hydra.ext.shacl.model;

/**
 * A SHACL property shape. See https://www.w3.org/TR/shacl/#property-shapes
 */
public class PropertyShape {
  public final hydra.ext.shacl.model.CommonProperties common;
  
  /**
   * Any property shape -specific constraint parameters
   */
  public final hydra.ext.shacl.model.PropertyShapeConstraints constraints;
  
  /**
   * See https://www.w3.org/TR/shacl/#defaultValue
   */
  public final java.util.Optional<hydra.ext.rdf.syntax.Node> defaultValue;
  
  /**
   * See https://www.w3.org/TR/shacl/#name
   */
  public final hydra.ext.rdf.syntax.LangStrings description;
  
  /**
   * See https://www.w3.org/TR/shacl/#name
   */
  public final hydra.ext.rdf.syntax.LangStrings name;
  
  /**
   * See https://www.w3.org/TR/shacl/#order
   */
  public final java.util.Optional<java.math.BigInteger> order;
  
  public final hydra.ext.rdf.syntax.Resource path;
  
  public PropertyShape (hydra.ext.shacl.model.CommonProperties common, hydra.ext.shacl.model.PropertyShapeConstraints constraints, java.util.Optional<hydra.ext.rdf.syntax.Node> defaultValue, hydra.ext.rdf.syntax.LangStrings description, hydra.ext.rdf.syntax.LangStrings name, java.util.Optional<java.math.BigInteger> order, hydra.ext.rdf.syntax.Resource path) {
    this.common = common;
    this.constraints = constraints;
    this.defaultValue = defaultValue;
    this.description = description;
    this.name = name;
    this.order = order;
    this.path = path;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof PropertyShape)) {
      return false;
    }
    PropertyShape o = (PropertyShape) (other);
    return common.equals(o.common) && constraints.equals(o.constraints) && defaultValue.equals(o.defaultValue) && description.equals(o.description) && name.equals(o.name) && order.equals(o.order) && path.equals(o.path);
  }
  
  @Override
  public int hashCode() {
    return 2 * common.hashCode() + 3 * constraints.hashCode() + 5 * defaultValue.hashCode() + 7 * description.hashCode() + 11 * name.hashCode() + 13 * order.hashCode() + 17 * path.hashCode();
  }
  
  public PropertyShape withCommon(hydra.ext.shacl.model.CommonProperties common) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withConstraints(hydra.ext.shacl.model.PropertyShapeConstraints constraints) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withDefaultValue(java.util.Optional<hydra.ext.rdf.syntax.Node> defaultValue) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withDescription(hydra.ext.rdf.syntax.LangStrings description) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withName(hydra.ext.rdf.syntax.LangStrings name) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withOrder(java.util.Optional<java.math.BigInteger> order) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
  
  public PropertyShape withPath(hydra.ext.rdf.syntax.Resource path) {
    return new PropertyShape(common, constraints, defaultValue, description, name, order, path);
  }
}