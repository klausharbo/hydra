package hydra.ext.java.syntax;

public class AnnotationTypeElementDeclaration {
  public final java.util.List<hydra.ext.java.syntax.AnnotationTypeElementModifier> modifiers;
  
  public final hydra.ext.java.syntax.UnannType type;
  
  public final hydra.ext.java.syntax.Identifier identifier;
  
  public final java.util.Optional<hydra.ext.java.syntax.Dims> dims;
  
  public final java.util.Optional<hydra.ext.java.syntax.DefaultValue> default_;
  
  public AnnotationTypeElementDeclaration (java.util.List<hydra.ext.java.syntax.AnnotationTypeElementModifier> modifiers, hydra.ext.java.syntax.UnannType type, hydra.ext.java.syntax.Identifier identifier, java.util.Optional<hydra.ext.java.syntax.Dims> dims, java.util.Optional<hydra.ext.java.syntax.DefaultValue> default_) {
    this.modifiers = modifiers;
    this.type = type;
    this.identifier = identifier;
    this.dims = dims;
    this.default_ = default_;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof AnnotationTypeElementDeclaration)) {
      return false;
    }
    AnnotationTypeElementDeclaration o = (AnnotationTypeElementDeclaration) (other);
    return modifiers.equals(o.modifiers) && type.equals(o.type) && identifier.equals(o.identifier) && dims.equals(o.dims) && default_.equals(o.default_);
  }
  
  @Override
  public int hashCode() {
    return 2 * modifiers.hashCode() + 3 * type.hashCode() + 5 * identifier.hashCode() + 7 * dims.hashCode() + 11 * default_.hashCode();
  }
  
  public AnnotationTypeElementDeclaration withModifiers(java.util.List<hydra.ext.java.syntax.AnnotationTypeElementModifier> modifiers) {
    return new AnnotationTypeElementDeclaration(modifiers, type, identifier, dims, default_);
  }
  
  public AnnotationTypeElementDeclaration withType(hydra.ext.java.syntax.UnannType type) {
    return new AnnotationTypeElementDeclaration(modifiers, type, identifier, dims, default_);
  }
  
  public AnnotationTypeElementDeclaration withIdentifier(hydra.ext.java.syntax.Identifier identifier) {
    return new AnnotationTypeElementDeclaration(modifiers, type, identifier, dims, default_);
  }
  
  public AnnotationTypeElementDeclaration withDims(java.util.Optional<hydra.ext.java.syntax.Dims> dims) {
    return new AnnotationTypeElementDeclaration(modifiers, type, identifier, dims, default_);
  }
  
  public AnnotationTypeElementDeclaration withDefault(java.util.Optional<hydra.ext.java.syntax.DefaultValue> default_) {
    return new AnnotationTypeElementDeclaration(modifiers, type, identifier, dims, default_);
  }
}