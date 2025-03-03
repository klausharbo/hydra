package hydra.ext.java.syntax;

public class VariableDeclarator {
  public final hydra.ext.java.syntax.VariableDeclaratorId id;
  
  public final java.util.Optional<hydra.ext.java.syntax.VariableInitializer> initializer;
  
  public VariableDeclarator (hydra.ext.java.syntax.VariableDeclaratorId id, java.util.Optional<hydra.ext.java.syntax.VariableInitializer> initializer) {
    this.id = id;
    this.initializer = initializer;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof VariableDeclarator)) {
      return false;
    }
    VariableDeclarator o = (VariableDeclarator) (other);
    return id.equals(o.id) && initializer.equals(o.initializer);
  }
  
  @Override
  public int hashCode() {
    return 2 * id.hashCode() + 3 * initializer.hashCode();
  }
  
  public VariableDeclarator withId(hydra.ext.java.syntax.VariableDeclaratorId id) {
    return new VariableDeclarator(id, initializer);
  }
  
  public VariableDeclarator withInitializer(java.util.Optional<hydra.ext.java.syntax.VariableInitializer> initializer) {
    return new VariableDeclarator(id, initializer);
  }
}