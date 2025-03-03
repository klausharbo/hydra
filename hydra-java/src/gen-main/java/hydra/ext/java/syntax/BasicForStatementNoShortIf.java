package hydra.ext.java.syntax;

public class BasicForStatementNoShortIf {
  public final hydra.ext.java.syntax.ForCond cond;
  
  public final hydra.ext.java.syntax.StatementNoShortIf body;
  
  public BasicForStatementNoShortIf (hydra.ext.java.syntax.ForCond cond, hydra.ext.java.syntax.StatementNoShortIf body) {
    this.cond = cond;
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicForStatementNoShortIf)) {
      return false;
    }
    BasicForStatementNoShortIf o = (BasicForStatementNoShortIf) (other);
    return cond.equals(o.cond) && body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * cond.hashCode() + 3 * body.hashCode();
  }
  
  public BasicForStatementNoShortIf withCond(hydra.ext.java.syntax.ForCond cond) {
    return new BasicForStatementNoShortIf(cond, body);
  }
  
  public BasicForStatementNoShortIf withBody(hydra.ext.java.syntax.StatementNoShortIf body) {
    return new BasicForStatementNoShortIf(cond, body);
  }
}