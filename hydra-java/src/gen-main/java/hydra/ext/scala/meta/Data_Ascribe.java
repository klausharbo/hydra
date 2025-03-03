package hydra.ext.scala.meta;

public class Data_Ascribe {
  public final hydra.ext.scala.meta.Data expr;
  
  public final hydra.ext.scala.meta.Type tpe;
  
  public Data_Ascribe (hydra.ext.scala.meta.Data expr, hydra.ext.scala.meta.Type tpe) {
    this.expr = expr;
    this.tpe = tpe;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Data_Ascribe)) {
      return false;
    }
    Data_Ascribe o = (Data_Ascribe) (other);
    return expr.equals(o.expr) && tpe.equals(o.tpe);
  }
  
  @Override
  public int hashCode() {
    return 2 * expr.hashCode() + 3 * tpe.hashCode();
  }
  
  public Data_Ascribe withExpr(hydra.ext.scala.meta.Data expr) {
    return new Data_Ascribe(expr, tpe);
  }
  
  public Data_Ascribe withTpe(hydra.ext.scala.meta.Type tpe) {
    return new Data_Ascribe(expr, tpe);
  }
}