package hydra.ext.scala.meta;

public class Data_Param {
  public final java.util.List<hydra.ext.scala.meta.Mod> mods;
  
  public final hydra.ext.scala.meta.Name name;
  
  public final java.util.Optional<hydra.ext.scala.meta.Type> decltpe;
  
  public final java.util.Optional<hydra.ext.scala.meta.Data> default_;
  
  public Data_Param (java.util.List<hydra.ext.scala.meta.Mod> mods, hydra.ext.scala.meta.Name name, java.util.Optional<hydra.ext.scala.meta.Type> decltpe, java.util.Optional<hydra.ext.scala.meta.Data> default_) {
    this.mods = mods;
    this.name = name;
    this.decltpe = decltpe;
    this.default_ = default_;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Data_Param)) {
      return false;
    }
    Data_Param o = (Data_Param) (other);
    return mods.equals(o.mods) && name.equals(o.name) && decltpe.equals(o.decltpe) && default_.equals(o.default_);
  }
  
  @Override
  public int hashCode() {
    return 2 * mods.hashCode() + 3 * name.hashCode() + 5 * decltpe.hashCode() + 7 * default_.hashCode();
  }
  
  public Data_Param withMods(java.util.List<hydra.ext.scala.meta.Mod> mods) {
    return new Data_Param(mods, name, decltpe, default_);
  }
  
  public Data_Param withName(hydra.ext.scala.meta.Name name) {
    return new Data_Param(mods, name, decltpe, default_);
  }
  
  public Data_Param withDecltpe(java.util.Optional<hydra.ext.scala.meta.Type> decltpe) {
    return new Data_Param(mods, name, decltpe, default_);
  }
  
  public Data_Param withDefault(java.util.Optional<hydra.ext.scala.meta.Data> default_) {
    return new Data_Param(mods, name, decltpe, default_);
  }
}