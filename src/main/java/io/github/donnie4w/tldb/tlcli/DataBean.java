/**
 * Autogenerated by Thrift Compiler (0.18.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package io.github.donnie4w.tldb.tlcli;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.18.1)", date = "2023-10-25")
public class DataBean implements org.apache.thrift.TBase<DataBean, DataBean._Fields>, java.io.Serializable, Cloneable, Comparable<DataBean> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataBean");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField T_BEAN_FIELD_DESC = new org.apache.thrift.protocol.TField("tBean", org.apache.thrift.protocol.TType.MAP, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DataBeanStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DataBeanTupleSchemeFactory();

  public long id; // required
  public @org.apache.thrift.annotation.Nullable java.util.Map<String,java.nio.ByteBuffer> tBean; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    T_BEAN((short)2, "tBean");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // T_BEAN
          return T_BEAN;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.T_BEAN};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.T_BEAN, new org.apache.thrift.meta_data.FieldMetaData("tBean", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING            , true))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataBean.class, metaDataMap);
  }

  public DataBean() {
  }

  public DataBean(
    long id)
  {
    this();
    this.id = id;
    setIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DataBean(DataBean other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetTBean()) {
      java.util.Map<String,java.nio.ByteBuffer> __this__tBean = new java.util.HashMap<String,java.nio.ByteBuffer>(other.tBean);
      this.tBean = __this__tBean;
    }
  }

  @Override
  public DataBean deepCopy() {
    return new DataBean(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.tBean = null;
  }

  public long getId() {
    return this.id;
  }

  public DataBean setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getTBeanSize() {
    return (this.tBean == null) ? 0 : this.tBean.size();
  }

  public void putToTBean(String key, java.nio.ByteBuffer val) {
    if (this.tBean == null) {
      this.tBean = new java.util.HashMap<String,java.nio.ByteBuffer>();
    }
    this.tBean.put(key, val);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Map<String,java.nio.ByteBuffer> getTBean() {
    return this.tBean;
  }

  public DataBean setTBean(@org.apache.thrift.annotation.Nullable java.util.Map<String,java.nio.ByteBuffer> tBean) {
    this.tBean = tBean;
    return this;
  }

  public void unsetTBean() {
    this.tBean = null;
  }

  /** Returns true if field tBean is set (has been assigned a value) and false otherwise */
  public boolean isSetTBean() {
    return this.tBean != null;
  }

  public void setTBeanIsSet(boolean value) {
    if (!value) {
      this.tBean = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case T_BEAN:
      if (value == null) {
        unsetTBean();
      } else {
        setTBean((java.util.Map<String,java.nio.ByteBuffer>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case T_BEAN:
      return getTBean();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case T_BEAN:
      return isSetTBean();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that instanceof DataBean)
      return this.equals((DataBean)that);
    return false;
  }

  public boolean equals(DataBean that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_tBean = true && this.isSetTBean();
    boolean that_present_tBean = true && that.isSetTBean();
    if (this_present_tBean || that_present_tBean) {
      if (!(this_present_tBean && that_present_tBean))
        return false;
      if (!this.tBean.equals(that.tBean))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + ((isSetTBean()) ? 131071 : 524287);
    if (isSetTBean())
      hashCode = hashCode * 8191 + tBean.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(DataBean other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.compare(isSetId(), other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetTBean(), other.isSetTBean());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTBean()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tBean, other.tBean);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DataBean(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (isSetTBean()) {
      if (!first) sb.append(", ");
      sb.append("tBean:");
      if (this.tBean == null) {
        sb.append("null");
      } else {
        sb.append(this.tBean);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'id' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DataBeanStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public DataBeanStandardScheme getScheme() {
      return new DataBeanStandardScheme();
    }
  }

  private static class DataBeanStandardScheme extends org.apache.thrift.scheme.StandardScheme<DataBean> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, DataBean struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // T_BEAN
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map20 = iprot.readMapBegin();
                struct.tBean = new java.util.HashMap<String,java.nio.ByteBuffer>(2*_map20.size);
                @org.apache.thrift.annotation.Nullable String _key21;
                @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _val22;
                for (int _i23 = 0; _i23 < _map20.size; ++_i23)
                {
                  _key21 = iprot.readString();
                  _val22 = iprot.readBinary();
                  struct.tBean.put(_key21, _val22);
                }
                iprot.readMapEnd();
              }
              struct.setTBeanIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, DataBean struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.tBean != null) {
        if (struct.isSetTBean()) {
          oprot.writeFieldBegin(T_BEAN_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.tBean.size()));
            for (java.util.Map.Entry<String, java.nio.ByteBuffer> _iter24 : struct.tBean.entrySet())
            {
              oprot.writeString(_iter24.getKey());
              oprot.writeBinary(_iter24.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DataBeanTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public DataBeanTupleScheme getScheme() {
      return new DataBeanTupleScheme();
    }
  }

  private static class DataBeanTupleScheme extends org.apache.thrift.scheme.TupleScheme<DataBean> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DataBean struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.id);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTBean()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetTBean()) {
        {
          oprot.writeI32(struct.tBean.size());
          for (java.util.Map.Entry<String, java.nio.ByteBuffer> _iter25 : struct.tBean.entrySet())
          {
            oprot.writeString(_iter25.getKey());
            oprot.writeBinary(_iter25.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DataBean struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map26 = iprot.readMapBegin(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING); 
          struct.tBean = new java.util.HashMap<String,java.nio.ByteBuffer>(2*_map26.size);
          @org.apache.thrift.annotation.Nullable String _key27;
          @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _val28;
          for (int _i29 = 0; _i29 < _map26.size; ++_i29)
          {
            _key27 = iprot.readString();
            _val28 = iprot.readBinary();
            struct.tBean.put(_key27, _val28);
          }
        }
        struct.setTBeanIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

