package hydra.ext.scala.meta;

public class Case {
  public final hydra.ext.scala.meta.Pat pat;
  
  public final java.util.Optional<hydra.ext.scala.meta.Data> cond;
  
  public final hydra.ext.scala.meta.Data body;
  
  public Case (hydra.ext.scala.meta.Pat pat, java.util.Optional<hydra.ext.scala.meta.Data> cond, hydra.ext.scala.meta.Data body) {
    this.pat = pat;
    this.cond = cond;
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Case)) {
      return false;
    }
    Case o = (Case) (other);
    return pat.equals(o.pat) && cond.equals(o.cond) && body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * pat.hashCode() + 3 * cond.hashCode() + 5 * body.hashCode();
  }
  
  public Case withPat(hydra.ext.scala.meta.Pat pat) {
    return new Case(pat, cond, body);
  }
  
  public Case withCond(java.util.Optional<hydra.ext.scala.meta.Data> cond) {
    return new Case(pat, cond, body);
  }
  
  public Case withBody(hydra.ext.scala.meta.Data body) {
    return new Case(pat, cond, body);
  }
}