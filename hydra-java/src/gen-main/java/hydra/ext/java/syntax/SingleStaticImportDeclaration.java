package hydra.ext.java.syntax;

public class SingleStaticImportDeclaration {
  public final hydra.ext.java.syntax.TypeName typeName;
  
  public final hydra.ext.java.syntax.Identifier identifier;
  
  public SingleStaticImportDeclaration (hydra.ext.java.syntax.TypeName typeName, hydra.ext.java.syntax.Identifier identifier) {
    this.typeName = typeName;
    this.identifier = identifier;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof SingleStaticImportDeclaration)) {
      return false;
    }
    SingleStaticImportDeclaration o = (SingleStaticImportDeclaration) (other);
    return typeName.equals(o.typeName) && identifier.equals(o.identifier);
  }
  
  @Override
  public int hashCode() {
    return 2 * typeName.hashCode() + 3 * identifier.hashCode();
  }
  
  public SingleStaticImportDeclaration withTypeName(hydra.ext.java.syntax.TypeName typeName) {
    return new SingleStaticImportDeclaration(typeName, identifier);
  }
  
  public SingleStaticImportDeclaration withIdentifier(hydra.ext.java.syntax.Identifier identifier) {
    return new SingleStaticImportDeclaration(typeName, identifier);
  }
}