package hydra.ext.scala.meta;

public abstract class Mod {
  private Mod () {
  
  }
  
  public abstract <R> R accept(Visitor<R> visitor) ;
  
  public interface Visitor<R> {
    R visit(Annot instance) ;
    
    R visit(Private instance) ;
    
    R visit(Protected instance) ;
    
    R visit(Implicit instance) ;
    
    R visit(Final instance) ;
    
    R visit(Sealed instance) ;
    
    R visit(Open instance) ;
    
    R visit(Super instance) ;
    
    R visit(Override_ instance) ;
    
    R visit(Case instance) ;
    
    R visit(Abstract instance) ;
    
    R visit(Covariant instance) ;
    
    R visit(Contravariant instance) ;
    
    R visit(Lazy instance) ;
    
    R visit(ValParam instance) ;
    
    R visit(VarParam instance) ;
    
    R visit(Infix instance) ;
    
    R visit(Inline instance) ;
    
    R visit(Using instance) ;
    
    R visit(Opaque instance) ;
    
    R visit(Transparent instance) ;
  }
  
  public interface PartialVisitor<R> extends Visitor<R> {
    default R otherwise(Mod instance) {
      throw new IllegalStateException("Non-exhaustive patterns when matching: " + (instance));
    }
    
    default R visit(Annot instance) {
      return otherwise((instance));
    }
    
    default R visit(Private instance) {
      return otherwise((instance));
    }
    
    default R visit(Protected instance) {
      return otherwise((instance));
    }
    
    default R visit(Implicit instance) {
      return otherwise((instance));
    }
    
    default R visit(Final instance) {
      return otherwise((instance));
    }
    
    default R visit(Sealed instance) {
      return otherwise((instance));
    }
    
    default R visit(Open instance) {
      return otherwise((instance));
    }
    
    default R visit(Super instance) {
      return otherwise((instance));
    }
    
    default R visit(Override_ instance) {
      return otherwise((instance));
    }
    
    default R visit(Case instance) {
      return otherwise((instance));
    }
    
    default R visit(Abstract instance) {
      return otherwise((instance));
    }
    
    default R visit(Covariant instance) {
      return otherwise((instance));
    }
    
    default R visit(Contravariant instance) {
      return otherwise((instance));
    }
    
    default R visit(Lazy instance) {
      return otherwise((instance));
    }
    
    default R visit(ValParam instance) {
      return otherwise((instance));
    }
    
    default R visit(VarParam instance) {
      return otherwise((instance));
    }
    
    default R visit(Infix instance) {
      return otherwise((instance));
    }
    
    default R visit(Inline instance) {
      return otherwise((instance));
    }
    
    default R visit(Using instance) {
      return otherwise((instance));
    }
    
    default R visit(Opaque instance) {
      return otherwise((instance));
    }
    
    default R visit(Transparent instance) {
      return otherwise((instance));
    }
  }
  
  public static final class Annot extends hydra.ext.scala.meta.Mod {
    public final hydra.ext.scala.meta.Mod_Annot value;
    
    public Annot (hydra.ext.scala.meta.Mod_Annot value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Annot)) {
        return false;
      }
      Annot o = (Annot) (other);
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
  
  public static final class Private extends hydra.ext.scala.meta.Mod {
    public final hydra.ext.scala.meta.Mod_Private value;
    
    public Private (hydra.ext.scala.meta.Mod_Private value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Private)) {
        return false;
      }
      Private o = (Private) (other);
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
  
  public static final class Protected extends hydra.ext.scala.meta.Mod {
    public final hydra.ext.scala.meta.Mod_Protected value;
    
    public Protected (hydra.ext.scala.meta.Mod_Protected value) {
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Protected)) {
        return false;
      }
      Protected o = (Protected) (other);
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
  
  public static final class Implicit extends hydra.ext.scala.meta.Mod {
    public Implicit () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Implicit)) {
        return false;
      }
      Implicit o = (Implicit) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Final extends hydra.ext.scala.meta.Mod {
    public Final () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Final)) {
        return false;
      }
      Final o = (Final) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Sealed extends hydra.ext.scala.meta.Mod {
    public Sealed () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Sealed)) {
        return false;
      }
      Sealed o = (Sealed) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Open extends hydra.ext.scala.meta.Mod {
    public Open () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Open)) {
        return false;
      }
      Open o = (Open) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Super extends hydra.ext.scala.meta.Mod {
    public Super () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Super)) {
        return false;
      }
      Super o = (Super) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Override_ extends hydra.ext.scala.meta.Mod {
    public Override_ () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Override_)) {
        return false;
      }
      Override_ o = (Override_) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Case extends hydra.ext.scala.meta.Mod {
    public Case () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Case)) {
        return false;
      }
      Case o = (Case) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Abstract extends hydra.ext.scala.meta.Mod {
    public Abstract () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Abstract)) {
        return false;
      }
      Abstract o = (Abstract) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Covariant extends hydra.ext.scala.meta.Mod {
    public Covariant () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Covariant)) {
        return false;
      }
      Covariant o = (Covariant) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Contravariant extends hydra.ext.scala.meta.Mod {
    public Contravariant () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Contravariant)) {
        return false;
      }
      Contravariant o = (Contravariant) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Lazy extends hydra.ext.scala.meta.Mod {
    public Lazy () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Lazy)) {
        return false;
      }
      Lazy o = (Lazy) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class ValParam extends hydra.ext.scala.meta.Mod {
    public ValParam () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof ValParam)) {
        return false;
      }
      ValParam o = (ValParam) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class VarParam extends hydra.ext.scala.meta.Mod {
    public VarParam () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof VarParam)) {
        return false;
      }
      VarParam o = (VarParam) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Infix extends hydra.ext.scala.meta.Mod {
    public Infix () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Infix)) {
        return false;
      }
      Infix o = (Infix) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Inline extends hydra.ext.scala.meta.Mod {
    public Inline () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Inline)) {
        return false;
      }
      Inline o = (Inline) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Using extends hydra.ext.scala.meta.Mod {
    public Using () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Using)) {
        return false;
      }
      Using o = (Using) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Opaque extends hydra.ext.scala.meta.Mod {
    public Opaque () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Opaque)) {
        return false;
      }
      Opaque o = (Opaque) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
  
  public static final class Transparent extends hydra.ext.scala.meta.Mod {
    public Transparent () {
    
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Transparent)) {
        return false;
      }
      Transparent o = (Transparent) (other);
      return true;
    }
    
    @Override
    public int hashCode() {
      return 0;
    }
    
    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visit(this);
    }
  }
}