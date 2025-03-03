package hydra.ext.java.syntax;

public class ConstructorBody {
  public final java.util.Optional<hydra.ext.java.syntax.ExplicitConstructorInvocation> invocation;
  
  public final java.util.List<hydra.ext.java.syntax.BlockStatement> statements;
  
  public ConstructorBody (java.util.Optional<hydra.ext.java.syntax.ExplicitConstructorInvocation> invocation, java.util.List<hydra.ext.java.syntax.BlockStatement> statements) {
    this.invocation = invocation;
    this.statements = statements;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ConstructorBody)) {
      return false;
    }
    ConstructorBody o = (ConstructorBody) (other);
    return invocation.equals(o.invocation) && statements.equals(o.statements);
  }
  
  @Override
  public int hashCode() {
    return 2 * invocation.hashCode() + 3 * statements.hashCode();
  }
  
  public ConstructorBody withInvocation(java.util.Optional<hydra.ext.java.syntax.ExplicitConstructorInvocation> invocation) {
    return new ConstructorBody(invocation, statements);
  }
  
  public ConstructorBody withStatements(java.util.List<hydra.ext.java.syntax.BlockStatement> statements) {
    return new ConstructorBody(invocation, statements);
  }
}