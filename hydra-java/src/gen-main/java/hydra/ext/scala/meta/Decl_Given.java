package hydra.ext.scala.meta;

public class Decl_Given {
  public final java.util.List<hydra.ext.scala.meta.Mod> mods;
  
  public final hydra.ext.scala.meta.Data_Name name;
  
  public final java.util.List<hydra.ext.scala.meta.Type_Param> tparams;
  
  public final java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams;
  
  public final hydra.ext.scala.meta.Type decltpe;
  
  public Decl_Given (java.util.List<hydra.ext.scala.meta.Mod> mods, hydra.ext.scala.meta.Data_Name name, java.util.List<hydra.ext.scala.meta.Type_Param> tparams, java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams, hydra.ext.scala.meta.Type decltpe) {
    this.mods = mods;
    this.name = name;
    this.tparams = tparams;
    this.sparams = sparams;
    this.decltpe = decltpe;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Decl_Given)) {
      return false;
    }
    Decl_Given o = (Decl_Given) (other);
    return mods.equals(o.mods) && name.equals(o.name) && tparams.equals(o.tparams) && sparams.equals(o.sparams) && decltpe.equals(o.decltpe);
  }
  
  @Override
  public int hashCode() {
    return 2 * mods.hashCode() + 3 * name.hashCode() + 5 * tparams.hashCode() + 7 * sparams.hashCode() + 11 * decltpe.hashCode();
  }
  
  public Decl_Given withMods(java.util.List<hydra.ext.scala.meta.Mod> mods) {
    return new Decl_Given(mods, name, tparams, sparams, decltpe);
  }
  
  public Decl_Given withName(hydra.ext.scala.meta.Data_Name name) {
    return new Decl_Given(mods, name, tparams, sparams, decltpe);
  }
  
  public Decl_Given withTparams(java.util.List<hydra.ext.scala.meta.Type_Param> tparams) {
    return new Decl_Given(mods, name, tparams, sparams, decltpe);
  }
  
  public Decl_Given withSparams(java.util.List<java.util.List<hydra.ext.scala.meta.Data_Param>> sparams) {
    return new Decl_Given(mods, name, tparams, sparams, decltpe);
  }
  
  public Decl_Given withDecltpe(hydra.ext.scala.meta.Type decltpe) {
    return new Decl_Given(mods, name, tparams, sparams, decltpe);
  }
}