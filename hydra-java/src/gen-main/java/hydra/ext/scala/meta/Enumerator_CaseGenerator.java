package hydra.ext.scala.meta;

public class Enumerator_CaseGenerator {
  public final hydra.ext.scala.meta.Pat pat;
  
  public final hydra.ext.scala.meta.Data rhs;
  
  public Enumerator_CaseGenerator (hydra.ext.scala.meta.Pat pat, hydra.ext.scala.meta.Data rhs) {
    this.pat = pat;
    this.rhs = rhs;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Enumerator_CaseGenerator)) {
      return false;
    }
    Enumerator_CaseGenerator o = (Enumerator_CaseGenerator) (other);
    return pat.equals(o.pat) && rhs.equals(o.rhs);
  }
  
  @Override
  public int hashCode() {
    return 2 * pat.hashCode() + 3 * rhs.hashCode();
  }
  
  public Enumerator_CaseGenerator withPat(hydra.ext.scala.meta.Pat pat) {
    return new Enumerator_CaseGenerator(pat, rhs);
  }
  
  public Enumerator_CaseGenerator withRhs(hydra.ext.scala.meta.Data rhs) {
    return new Enumerator_CaseGenerator(pat, rhs);
  }
}