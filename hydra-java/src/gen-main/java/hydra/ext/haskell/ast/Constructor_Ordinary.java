package hydra.ext.haskell.ast;

/**
 * An ordinary (positional) data constructor
 */
public class Constructor_Ordinary {
  public final hydra.ext.haskell.ast.Name name;
  
  public final java.util.List<hydra.ext.haskell.ast.Type> fields;
  
  public Constructor_Ordinary (hydra.ext.haskell.ast.Name name, java.util.List<hydra.ext.haskell.ast.Type> fields) {
    this.name = name;
    this.fields = fields;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Constructor_Ordinary)) {
      return false;
    }
    Constructor_Ordinary o = (Constructor_Ordinary) (other);
    return name.equals(o.name) && fields.equals(o.fields);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode() + 3 * fields.hashCode();
  }
  
  public Constructor_Ordinary withName(hydra.ext.haskell.ast.Name name) {
    return new Constructor_Ordinary(name, fields);
  }
  
  public Constructor_Ordinary withFields(java.util.List<hydra.ext.haskell.ast.Type> fields) {
    return new Constructor_Ordinary(name, fields);
  }
}