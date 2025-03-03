package hydra.ext.java.syntax;

public class FieldAccess {
  public final hydra.ext.java.syntax.FieldAccess_Qualifier qualifier;
  
  public final hydra.ext.java.syntax.Identifier identifier;
  
  public FieldAccess (hydra.ext.java.syntax.FieldAccess_Qualifier qualifier, hydra.ext.java.syntax.Identifier identifier) {
    this.qualifier = qualifier;
    this.identifier = identifier;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof FieldAccess)) {
      return false;
    }
    FieldAccess o = (FieldAccess) (other);
    return qualifier.equals(o.qualifier) && identifier.equals(o.identifier);
  }
  
  @Override
  public int hashCode() {
    return 2 * qualifier.hashCode() + 3 * identifier.hashCode();
  }
  
  public FieldAccess withQualifier(hydra.ext.java.syntax.FieldAccess_Qualifier qualifier) {
    return new FieldAccess(qualifier, identifier);
  }
  
  public FieldAccess withIdentifier(hydra.ext.java.syntax.Identifier identifier) {
    return new FieldAccess(qualifier, identifier);
  }
}