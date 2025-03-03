package hydra.ext.haskell.ast;

/**
 * A data declaration together with any comments
 */
public class DeclarationWithComments {
  public final hydra.ext.haskell.ast.Declaration body;
  
  public final java.util.Optional<String> comments;
  
  public DeclarationWithComments (hydra.ext.haskell.ast.Declaration body, java.util.Optional<String> comments) {
    this.body = body;
    this.comments = comments;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof DeclarationWithComments)) {
      return false;
    }
    DeclarationWithComments o = (DeclarationWithComments) (other);
    return body.equals(o.body) && comments.equals(o.comments);
  }
  
  @Override
  public int hashCode() {
    return 2 * body.hashCode() + 3 * comments.hashCode();
  }
  
  public DeclarationWithComments withBody(hydra.ext.haskell.ast.Declaration body) {
    return new DeclarationWithComments(body, comments);
  }
  
  public DeclarationWithComments withComments(java.util.Optional<String> comments) {
    return new DeclarationWithComments(body, comments);
  }
}