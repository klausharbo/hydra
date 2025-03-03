package hydra.ext.scala.meta;

public class Data_QuotedMacroExpr {
  public final hydra.ext.scala.meta.Data body;
  
  public Data_QuotedMacroExpr (hydra.ext.scala.meta.Data body) {
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Data_QuotedMacroExpr)) {
      return false;
    }
    Data_QuotedMacroExpr o = (Data_QuotedMacroExpr) (other);
    return body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * body.hashCode();
  }
}