package hydra.ext.scala.meta;

public class Defn_Given {
  public final java.util.List<hydra.ext.scala.meta.Mod> mods;
  
  public final hydra.ext.scala.meta.Name name;
  
  public final java.util.List<java.util.List<hydra.ext.scala.meta.Type_Param>> tparams;
  
  public final java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams;
  
  public final hydra.ext.scala.meta.Template templ;
  
  public Defn_Given (java.util.List<hydra.ext.scala.meta.Mod> mods, hydra.ext.scala.meta.Name name, java.util.List<java.util.List<hydra.ext.scala.meta.Type_Param>> tparams, java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams, hydra.ext.scala.meta.Template templ) {
    this.mods = mods;
    this.name = name;
    this.tparams = tparams;
    this.sparams = sparams;
    this.templ = templ;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Defn_Given)) {
      return false;
    }
    Defn_Given o = (Defn_Given) (other);
    return mods.equals(o.mods) && name.equals(o.name) && tparams.equals(o.tparams) && sparams.equals(o.sparams) && templ.equals(o.templ);
  }
  
  @Override
  public int hashCode() {
    return 2 * mods.hashCode() + 3 * name.hashCode() + 5 * tparams.hashCode() + 7 * sparams.hashCode() + 11 * templ.hashCode();
  }
  
  public Defn_Given withMods(java.util.List<hydra.ext.scala.meta.Mod> mods) {
    return new Defn_Given(mods, name, tparams, sparams, templ);
  }
  
  public Defn_Given withName(hydra.ext.scala.meta.Name name) {
    return new Defn_Given(mods, name, tparams, sparams, templ);
  }
  
  public Defn_Given withTparams(java.util.List<java.util.List<hydra.ext.scala.meta.Type_Param>> tparams) {
    return new Defn_Given(mods, name, tparams, sparams, templ);
  }
  
  public Defn_Given withSparams(java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams) {
    return new Defn_Given(mods, name, tparams, sparams, templ);
  }
  
  public Defn_Given withTempl(hydra.ext.scala.meta.Template templ) {
    return new Defn_Given(mods, name, tparams, sparams, templ);
  }
}