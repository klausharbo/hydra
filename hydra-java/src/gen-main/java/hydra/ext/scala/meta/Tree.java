package hydra.ext.scala.meta;

public abstract class Tree {
  private Tree () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Ref instance) ;
    
    R visit(Stat instance) ;
    
    R visit(Type instance) ;
    
    R visit(Bounds instance) ;
    
    R visit(Pat instance) ;
    
    R visit(Member instance) ;
    
    R visit(Ctor instance) ;
    
    R visit(Template instance) ;
    
    R visit(Mod instance) ;
    
    R visit(Enumerator instance) ;
    
    R visit(Importer instance) ;
    
    R visit(Importee instance) ;
    
    R visit(CaseTree instance) ;
    
    R visit(Source instance) ;
    
    R visit(Quasi instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Tree instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Ref instance) {
      return otherwise((instance));
    }
    
    default R visit(Stat instance) {
      return otherwise((instance));
    }
    
    default R visit(Type instance) {
      return otherwise((instance));
    }
    
    default R visit(Bounds instance) {
      return otherwise((instance));
    }
    
    default R visit(Pat instance) {
      return otherwise((instance));
    }
    
    default R visit(Member instance) {
      return otherwise((instance));
    }
    
    default R visit(Ctor instance) {
      return otherwise((instance));
    }
    
    default R visit(Template instance) {
      return otherwise((instance));
    }
    
    default R visit(Mod instance) {
      return otherwise((instance));
    }
    
    default R visit(Enumerator instance) {
      return otherwise((instance));
    }
    
    default R visit(Importer instance) {
      return otherwise((instance));
    }
    
    default R visit(Importee instance) {
      return otherwise((instance));
    }
    
    default R visit(CaseTree instance) {
      return otherwise((instance));
    }
    
    default R visit(Source instance) {
      return otherwise((instance));
    }
    
    default R visit(Quasi instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Ref extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Ref value;
    
    public Ref (hydra.ext.scala.meta.Ref value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Ref)) {
        return false;
      }
      Ref o = (Ref) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Stat extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Stat value;
    
    public Stat (hydra.ext.scala.meta.Stat value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Stat)) {
        return false;
      }
      Stat o = (Stat) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Type extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Type value;
    
    public Type (hydra.ext.scala.meta.Type value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Type)) {
        return false;
      }
      Type o = (Type) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Bounds extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Type_Bounds value;
    
    public Bounds (hydra.ext.scala.meta.Type_Bounds value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Bounds)) {
        return false;
      }
      Bounds o = (Bounds) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Pat extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Pat value;
    
    public Pat (hydra.ext.scala.meta.Pat value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Pat)) {
        return false;
      }
      Pat o = (Pat) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Member extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Member value;
    
    public Member (hydra.ext.scala.meta.Member value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Member)) {
        return false;
      }
      Member o = (Member) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Ctor extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Ctor value;
    
    public Ctor (hydra.ext.scala.meta.Ctor value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Ctor)) {
        return false;
      }
      Ctor o = (Ctor) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Template extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Template value;
    
    public Template (hydra.ext.scala.meta.Template value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Template)) {
        return false;
      }
      Template o = (Template) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Mod extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Mod value;
    
    public Mod (hydra.ext.scala.meta.Mod value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Mod)) {
        return false;
      }
      Mod o = (Mod) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Enumerator extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Enumerator value;
    
    public Enumerator (hydra.ext.scala.meta.Enumerator value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Enumerator)) {
        return false;
      }
      Enumerator o = (Enumerator) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Importer extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Importer value;
    
    public Importer (hydra.ext.scala.meta.Importer value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Importer)) {
        return false;
      }
      Importer o = (Importer) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Importee extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Importee value;
    
    public Importee (hydra.ext.scala.meta.Importee value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Importee)) {
        return false;
      }
      Importee o = (Importee) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class CaseTree extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.CaseTree value;
    
    public CaseTree (hydra.ext.scala.meta.CaseTree value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof CaseTree)) {
        return false;
      }
      CaseTree o = (CaseTree) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Source extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Source value;
    
    public Source (hydra.ext.scala.meta.Source value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Source)) {
        return false;
      }
      Source o = (Source) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Quasi extends hydra.ext.scala.meta.Tree {
    public final hydra.ext.scala.meta.Quasi value;
    
    public Quasi (hydra.ext.scala.meta.Quasi value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Quasi)) {
        return false;
      }
      Quasi o = (Quasi) (other);
      return value.equals(o.value);
    }
    
    @Override
    public int hashCode() {
      return 2 * value.hashCode();
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
}