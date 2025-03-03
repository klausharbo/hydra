package hydra.ext.scala.meta;

public class Defn_EnumCase {
  public final java.util.List<hydra.ext.scala.meta.Mod> mods;
  
  public final hydra.ext.scala.meta.Data_Name name;
  
  public final java.util.List<hydra.ext.scala.meta.Type_Param> tparams;
  
  public final hydra.ext.scala.meta.Ctor_Primary ctor;
  
  public final java.util.List<hydra.ext.scala.meta.Init> inits;
  
  public Defn_EnumCase (java.util.List<hydra.ext.scala.meta.Mod> mods, hydra.ext.scala.meta.Data_Name name, java.util.List<hydra.ext.scala.meta.Type_Param> tparams, hydra.ext.scala.meta.Ctor_Primary ctor, java.util.List<hydra.ext.scala.meta.Init> inits) {
    this.mods = mods;
    this.name = name;
    this.tparams = tparams;
    this.ctor = ctor;
    this.inits = inits;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Defn_EnumCase)) {
      return false;
    }
    Defn_EnumCase o = (Defn_EnumCase) (other);
    return mods.equals(o.mods) && name.equals(o.name) && tparams.equals(o.tparams) && ctor.equals(o.ctor) && inits.equals(o.inits);
  }
  
  @Override
  public int hashCode() {
    return 2 * mods.hashCode() + 3 * name.hashCode() + 5 * tparams.hashCode() + 7 * ctor.hashCode() + 11 * inits.hashCode();
  }
  
  public Defn_EnumCase withMods(java.util.List<hydra.ext.scala.meta.Mod> mods) {
    return new Defn_EnumCase(mods, name, tparams, ctor, inits);
  }
  
  public Defn_EnumCase withName(hydra.ext.scala.meta.Data_Name name) {
    return new Defn_EnumCase(mods, name, tparams, ctor, inits);
  }
  
  public Defn_EnumCase withTparams(java.util.List<hydra.ext.scala.meta.Type_Param> tparams) {
    return new Defn_EnumCase(mods, name, tparams, ctor, inits);
  }
  
  public Defn_EnumCase withCtor(hydra.ext.scala.meta.Ctor_Primary ctor) {
    return new Defn_EnumCase(mods, name, tparams, ctor, inits);
  }
  
  public Defn_EnumCase withInits(java.util.List<hydra.ext.scala.meta.Init> inits) {
    return new Defn_EnumCase(mods, name, tparams, ctor, inits);
  }
}