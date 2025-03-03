package hydra.ext.pegasus.pdl;

public class RecordField {
  public final hydra.ext.pegasus.pdl.FieldName name;
  
  public final hydra.ext.pegasus.pdl.Schema value;
  
  public final Boolean optional;
  
  public final java.util.Optional<hydra.ext.json.model.Value> default_;
  
  public final hydra.ext.pegasus.pdl.Annotations annotations;
  
  public RecordField (hydra.ext.pegasus.pdl.FieldName name, hydra.ext.pegasus.pdl.Schema value, Boolean optional, java.util.Optional<hydra.ext.json.model.Value> default_, hydra.ext.pegasus.pdl.Annotations annotations) {
    this.name = name;
    this.value = value;
    this.optional = optional;
    this.default_ = default_;
    this.annotations = annotations;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof RecordField)) {
      return false;
    }
    RecordField o = (RecordField) (other);
    return name.equals(o.name) && value.equals(o.value) && optional.equals(o.optional) && default_.equals(o.default_) && annotations.equals(o.annotations);
  }
  
  @Override
  public int hashCode() {
    return 2 * name.hashCode() + 3 * value.hashCode() + 5 * optional.hashCode() + 7 * default_.hashCode() + 11 * annotations.hashCode();
  }
  
  public RecordField withName(hydra.ext.pegasus.pdl.FieldName name) {
    return new RecordField(name, value, optional, default_, annotations);
  }
  
  public RecordField withValue(hydra.ext.pegasus.pdl.Schema value) {
    return new RecordField(name, value, optional, default_, annotations);
  }
  
  public RecordField withOptional(Boolean optional) {
    return new RecordField(name, value, optional, default_, annotations);
  }
  
  public RecordField withDefault(java.util.Optional<hydra.ext.json.model.Value> default_) {
    return new RecordField(name, value, optional, default_, annotations);
  }
  
  public RecordField withAnnotations(hydra.ext.pegasus.pdl.Annotations annotations) {
    return new RecordField(name, value, optional, default_, annotations);
  }
}