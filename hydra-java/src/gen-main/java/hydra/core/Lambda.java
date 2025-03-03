package hydra.core;

/**
 * A function abstraction (lambda)
 */
public class Lambda<M> {
  /**
   * The parameter of the lambda
   */
  public final hydra.core.Variable parameter;
  
  /**
   * The body of the lambda
   */
  public final hydra.core.Term<M> body;
  
  public Lambda (hydra.core.Variable parameter, hydra.core.Term<M> body) {
    this.parameter = parameter;
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Lambda)) {
      return false;
    }
    Lambda o = (Lambda) (other);
    return parameter.equals(o.parameter) && body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * parameter.hashCode() + 3 * body.hashCode();
  }
  
  public Lambda withParameter(hydra.core.Variable parameter) {
    return new Lambda(parameter, body);
  }
  
  public Lambda withBody(hydra.core.Term<M> body) {
    return new Lambda(parameter, body);
  }
}