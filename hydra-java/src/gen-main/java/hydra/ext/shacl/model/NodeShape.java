package hydra.ext.shacl.model;

/**
 * A SHACL node shape. See https://www.w3.org/TR/shacl/#node-shapes
 */
public class NodeShape {
  public final hydra.ext.shacl.model.CommonProperties common;
  
  public NodeShape (hydra.ext.shacl.model.CommonProperties common) {
    this.common = common;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof NodeShape)) {
      return false;
    }
    NodeShape o = (NodeShape) (other);
    return common.equals(o.common);
  }
  
  @Override
  public int hashCode() {
    return 2 * common.hashCode();
  }
}