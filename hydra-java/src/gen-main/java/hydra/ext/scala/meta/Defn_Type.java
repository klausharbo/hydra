package hydra.ext.scala.meta;

public class Defn_Type {
  public final java.util.List<hydra.ext.scala.meta.Mod> mods;
  
  public final hydra.ext.scala.meta.Type_Name name;
  
  public final java.util.List<hydra.ext.scala.meta.Type_Param> tparams;
  
  public final hydra.ext.scala.meta.Type body;
  
  public Defn_Type (java.util.List<hydra.ext.scala.meta.Mod> mods, hydra.ext.scala.meta.Type_Name name, java.util.List<hydra.ext.scala.meta.Type_Param> tparams, hydra.ext.scala.meta.Type body) {
    this.mods = mods;
    this.name = name;
    this.tparams = tparams;
    this.body = body;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Defn_Type)) {
      return false;
    }
    Defn_Type o = (Defn_Type) (other);
    return mods.equals(o.mods) && name.equals(o.name) && tparams.equals(o.tparams) && body.equals(o.body);
  }
  
  @Override
  public int hashCode() {
    return 2 * mods.hashCode() + 3 * name.hashCode() + 5 * tparams.hashCode() + 7 * body.hashCode();
  }
  
  public Defn_Type withMods(java.util.List<hydra.ext.scala.meta.Mod> mods) {
    return new Defn_Type(mods, name, tparams, body);
  }
  
  public Defn_Type withName(hydra.ext.scala.meta.Type_Name name) {
    return new Defn_Type(mods, name, tparams, body);
  }
  
  public Defn_Type withTparams(java.util.List<hydra.ext.scala.meta.Type_Param> tparams) {
    return new Defn_Type(mods, name, tparams, body);
  }
  
  public Defn_Type withBody(hydra.ext.scala.meta.Type body) {
    return new Defn_Type(mods, name, tparams, body);
  }
}