/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package kafka.bank.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class OperationLog extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8942032660266742240L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OperationLog\",\"namespace\":\"kafka.bank.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"logicalType\":\"UUID\"},{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"OperationType\",\"symbols\":[\"ACCEPT\",\"PROCESSING\",\"SUCCESS\",\"FAIL\"],\"default\":\"FAIL\"}},{\"name\":\"requestId\",\"type\":\"string\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OperationLog> ENCODER =
      new BinaryMessageEncoder<OperationLog>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OperationLog> DECODER =
      new BinaryMessageDecoder<OperationLog>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<OperationLog> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<OperationLog> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<OperationLog> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OperationLog>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this OperationLog to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a OperationLog from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a OperationLog instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static OperationLog fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence id;
  @Deprecated public kafka.bank.avro.OperationType type;
  @Deprecated public java.lang.CharSequence requestId;
  @Deprecated public java.lang.CharSequence description;
  @Deprecated public long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OperationLog() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param type The new value for type
   * @param requestId The new value for requestId
   * @param description The new value for description
   * @param timestamp The new value for timestamp
   */
  public OperationLog(java.lang.CharSequence id, kafka.bank.avro.OperationType type, java.lang.CharSequence requestId, java.lang.CharSequence description, java.lang.Long timestamp) {
    this.id = id;
    this.type = type;
    this.requestId = requestId;
    this.description = description;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return type;
    case 2: return requestId;
    case 3: return description;
    case 4: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: type = (kafka.bank.avro.OperationType)value$; break;
    case 2: requestId = (java.lang.CharSequence)value$; break;
    case 3: description = (java.lang.CharSequence)value$; break;
    case 4: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public kafka.bank.avro.OperationType getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(kafka.bank.avro.OperationType value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'requestId' field.
   * @return The value of the 'requestId' field.
   */
  public java.lang.CharSequence getRequestId() {
    return requestId;
  }


  /**
   * Sets the value of the 'requestId' field.
   * @param value the value to set.
   */
  public void setRequestId(java.lang.CharSequence value) {
    this.requestId = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return The value of the 'description' field.
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }


  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new OperationLog RecordBuilder.
   * @return A new OperationLog RecordBuilder
   */
  public static kafka.bank.avro.OperationLog.Builder newBuilder() {
    return new kafka.bank.avro.OperationLog.Builder();
  }

  /**
   * Creates a new OperationLog RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OperationLog RecordBuilder
   */
  public static kafka.bank.avro.OperationLog.Builder newBuilder(kafka.bank.avro.OperationLog.Builder other) {
    if (other == null) {
      return new kafka.bank.avro.OperationLog.Builder();
    } else {
      return new kafka.bank.avro.OperationLog.Builder(other);
    }
  }

  /**
   * Creates a new OperationLog RecordBuilder by copying an existing OperationLog instance.
   * @param other The existing instance to copy.
   * @return A new OperationLog RecordBuilder
   */
  public static kafka.bank.avro.OperationLog.Builder newBuilder(kafka.bank.avro.OperationLog other) {
    if (other == null) {
      return new kafka.bank.avro.OperationLog.Builder();
    } else {
      return new kafka.bank.avro.OperationLog.Builder(other);
    }
  }

  /**
   * RecordBuilder for OperationLog instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OperationLog>
    implements org.apache.avro.data.RecordBuilder<OperationLog> {

    private java.lang.CharSequence id;
    private kafka.bank.avro.OperationType type;
    private java.lang.CharSequence requestId;
    private java.lang.CharSequence description;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(kafka.bank.avro.OperationLog.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.requestId)) {
        this.requestId = data().deepCopy(fields()[2].schema(), other.requestId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing OperationLog instance
     * @param other The existing instance to copy.
     */
    private Builder(kafka.bank.avro.OperationLog other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.requestId)) {
        this.requestId = data().deepCopy(fields()[2].schema(), other.requestId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public kafka.bank.avro.OperationType getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder setType(kafka.bank.avro.OperationType value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'requestId' field.
      * @return The value.
      */
    public java.lang.CharSequence getRequestId() {
      return requestId;
    }


    /**
      * Sets the value of the 'requestId' field.
      * @param value The value of 'requestId'.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder setRequestId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.requestId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'requestId' field has been set.
      * @return True if the 'requestId' field has been set, false otherwise.
      */
    public boolean hasRequestId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'requestId' field.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder clearRequestId() {
      requestId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }


    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.description = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder clearDescription() {
      description = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder setTimestamp(long value) {
      validate(fields()[4], value);
      this.timestamp = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public kafka.bank.avro.OperationLog.Builder clearTimestamp() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OperationLog build() {
      try {
        OperationLog record = new OperationLog();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (kafka.bank.avro.OperationType) defaultValue(fields()[1]);
        record.requestId = fieldSetFlags()[2] ? this.requestId : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.description = fieldSetFlags()[3] ? this.description : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.timestamp = fieldSetFlags()[4] ? this.timestamp : (java.lang.Long) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OperationLog>
    WRITER$ = (org.apache.avro.io.DatumWriter<OperationLog>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OperationLog>
    READER$ = (org.apache.avro.io.DatumReader<OperationLog>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeEnum(this.type.ordinal());

    out.writeString(this.requestId);

    out.writeString(this.description);

    out.writeLong(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);

      this.type = kafka.bank.avro.OperationType.values()[in.readEnum()];

      this.requestId = in.readString(this.requestId instanceof Utf8 ? (Utf8)this.requestId : null);

      this.description = in.readString(this.description instanceof Utf8 ? (Utf8)this.description : null);

      this.timestamp = in.readLong();

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);
          break;

        case 1:
          this.type = kafka.bank.avro.OperationType.values()[in.readEnum()];
          break;

        case 2:
          this.requestId = in.readString(this.requestId instanceof Utf8 ? (Utf8)this.requestId : null);
          break;

        case 3:
          this.description = in.readString(this.description instanceof Utf8 ? (Utf8)this.description : null);
          break;

        case 4:
          this.timestamp = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









