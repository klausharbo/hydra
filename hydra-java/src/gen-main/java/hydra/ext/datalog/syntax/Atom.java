package hydra.ext.datalog.syntax;

public class Atom {
  public final hydra.ext.datalog.syntax.Relation relation;
  
  public final hydra.ext.datalog.syntax.TermList termList;
  
  public Atom (hydra.ext.datalog.syntax.Relation relation, hydra.ext.datalog.syntax.TermList termList) {
    this.relation = relation;
    this.termList = termList;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Atom)) {
      return false;
    }
    Atom o = (Atom) (other);
    return relation.equals(o.relation) && termList.equals(o.termList);
  }
  
  @Override
  public int hashCode() {
    return 2 * relation.hashCode() + 3 * termList.hashCode();
  }
  
  public Atom withRelation(hydra.ext.datalog.syntax.Relation relation) {
    return new Atom(relation, termList);
  }
  
  public Atom withTermList(hydra.ext.datalog.syntax.TermList termList) {
    return new Atom(relation, termList);
  }
}