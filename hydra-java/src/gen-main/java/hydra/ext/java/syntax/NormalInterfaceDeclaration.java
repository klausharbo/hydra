package hydra.ext.java.syntax;

public class NormalInterfaceDeclaration {
  public final java.util.List<hydra.ext.java.syntax.InterfaceModifier> modifiers;
  
  public final hydra.ext.java.syntax.TypeIdentifier identifier;
  
  public final java.util.List<hydra.ext.java.syntax.TypeParameter> parameters;
  
  public final java.util.List<hydra.ext.java.syntax.InterfaceType> extends_;
  
  public final hydra.ext.java.syntax.InterfaceBody body;
  
  public NormalInterfaceDeclaration (java.util.List<hydra.ext.java.syntax.InterfaceModifier> modifiers, hydra.ext.java.syntax.TypeIdentifier identifier, java.util.List<hydra.ext.java.syntax.TypeParameter> parameters, java.util.List<hydra.ext.java.syntax.InterfaceType> extends_, hydra.ext.java.syntax.InterfaceBody body) {
    this.modifiers = modifiers;
    this.identifier = identifier;
    this.parameters = parameters;
    this.extends_ = extends_;
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof NormalInterfaceDeclaration)) {
      return false;
    }
    NormalInterfaceDeclaration o = (NormalInterfaceDeclaration) (other);
    return modifiers.equals(o.modifiers) && identifier.equals(o.identifier) && parameters.equals(o.parameters) && extends_.equals(o.extends_) && body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * modifiers.hashCode() + 3 * identifier.hashCode() + 5 * parameters.hashCode() + 7 * extends_.hashCode() + 11 * body.hashCode();
  }
  
  public NormalInterfaceDeclaration withModifiers(java.util.List<hydra.ext.java.syntax.InterfaceModifier> modifiers) {
    return new NormalInterfaceDeclaration(modifiers, identifier, parameters, extends_, body);
  }
  
  public NormalInterfaceDeclaration withIdentifier(hydra.ext.java.syntax.TypeIdentifier identifier) {
    return new NormalInterfaceDeclaration(modifiers, identifier, parameters, extends_, body);
  }
  
  public NormalInterfaceDeclaration withParameters(java.util.List<hydra.ext.java.syntax.TypeParameter> parameters) {
    return new NormalInterfaceDeclaration(modifiers, identifier, parameters, extends_, body);
  }
  
  public NormalInterfaceDeclaration withExtends(java.util.List<hydra.ext.java.syntax.InterfaceType> extends_) {
    return new NormalInterfaceDeclaration(modifiers, identifier, parameters, extends_, body);
  }
  
  public NormalInterfaceDeclaration withBody(hydra.ext.java.syntax.InterfaceBody body) {
    return new NormalInterfaceDeclaration(modifiers, identifier, parameters, extends_, body);
  }
}