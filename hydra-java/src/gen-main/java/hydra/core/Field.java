package hydra.core;

/**
 * A labeled term
 */
public class Field<M> {
  public final hydra.core.FieldName name;
  
  public final hydra.core.Term<M> term;
  
  public Field (hydra.core.FieldName name, hydra.core.Term<M> term) {
    this.name = name;
    this.term = term;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Field)) {
      return false;
    }
    Field o = (Field) (other);
    return name.equals(o.name) && term.equals(o.term);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode() + 3 * term.hashCode();
  }
  
  public Field withName(hydra.core.FieldName name) {
    return new Field(name, term);
  }
  
  public Field withTerm(hydra.core.Term<M> term) {
    return new Field(name, term);
  }
}