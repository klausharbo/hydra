package hydra.ext.java.syntax;

public class TryStatement_WithFinally {
  public final hydra.ext.java.syntax.Block block;
  
  public final java.util.Optional<hydra.ext.java.syntax.Catches> catches;
  
  public final hydra.ext.java.syntax.Finally finally_;
  
  public TryStatement_WithFinally (hydra.ext.java.syntax.Block block, java.util.Optional<hydra.ext.java.syntax.Catches> catches, hydra.ext.java.syntax.Finally finally_) {
    this.block = block;
    this.catches = catches;
    this.finally_ = finally_;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof TryStatement_WithFinally)) {
      return false;
    }
    TryStatement_WithFinally o = (TryStatement_WithFinally) (other);
    return block.equals(o.block) && catches.equals(o.catches) && finally_.equals(o.finally_);
  }
  
  @Override
  public int hashCode() {
    return 2 * block.hashCode() + 3 * catches.hashCode() + 5 * finally_.hashCode();
  }
  
  public TryStatement_WithFinally withBlock(hydra.ext.java.syntax.Block block) {
    return new TryStatement_WithFinally(block, catches, finally_);
  }
  
  public TryStatement_WithFinally withCatches(java.util.Optional<hydra.ext.java.syntax.Catches> catches) {
    return new TryStatement_WithFinally(block, catches, finally_);
  }
  
  public TryStatement_WithFinally withFinally(hydra.ext.java.syntax.Finally finally_) {
    return new TryStatement_WithFinally(block, catches, finally_);
  }
}