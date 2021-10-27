package hydra.graph;

import hydra.core.Name;
import hydra.core.Term;

/**
 * A graph element, having a name, data term (value), and schema term (type)
 */
public class Element {
  /**
   * @type hydra/core.Name
   */
  public final Name name;
  
  /**
   * @type hydra/core.Term
   */
  public final Term schema;
  
  /**
   * @type hydra/core.Term
   */
  public final Term data;
  
  /**
   * Constructs an immutable Element object
   */
  public Element(Name name, Term schema, Term data) {
    this.name = name;
    this.schema = schema;
    this.data = data;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Element)) {
        return false;
    }
    Element o = (Element) other;
    return name.equals(o.name)
        && schema.equals(o.schema)
        && data.equals(o.data);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode()
        + 3 * schema.hashCode()
        + 5 * data.hashCode();
  }
  
  /**
   * Construct a new immutable Element object in which name is overridden
   */
  public Element withName(Name name) {
    return new Element(name, schema, data);
  }
  
  /**
   * Construct a new immutable Element object in which schema is overridden
   */
  public Element withSchema(Term schema) {
    return new Element(name, schema, data);
  }
  
  /**
   * Construct a new immutable Element object in which data is overridden
   */
  public Element withData(Term data) {
    return new Element(name, schema, data);
  }
}
