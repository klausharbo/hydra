package hydra.ext.scala.meta;

public class Type_ContextFunction {
  public final java.util.List<hydra.ext.scala.meta.Type> params;
  
  public final hydra.ext.scala.meta.Type res;
  
  public Type_ContextFunction (java.util.List<hydra.ext.scala.meta.Type> params, hydra.ext.scala.meta.Type res) {
    this.params = params;
    this.res = res;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Type_ContextFunction)) {
      return false;
    }
    Type_ContextFunction o = (Type_ContextFunction) (other);
    return params.equals(o.params) && res.equals(o.res);
  }
  
  @Override
  public int hashCode() {
    return 2 * params.hashCode() + 3 * res.hashCode();
  }
  
  public Type_ContextFunction withParams(java.util.List<hydra.ext.scala.meta.Type> params) {
    return new Type_ContextFunction(params, res);
  }
  
  public Type_ContextFunction withRes(hydra.ext.scala.meta.Type res) {
    return new Type_ContextFunction(params, res);
  }
}