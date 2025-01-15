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
public class PaymentRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5952761712073653107L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentRequest\",\"namespace\":\"kafka.bank.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"logicalType\":\"UUID\"},{\"name\":\"payerAccountNumber\",\"type\":\"string\"},{\"name\":\"receiverAccountNumber\",\"type\":\"string\"},{\"name\":\"cashAmount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":18,\"scale\":2}},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();
static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<PaymentRequest> ENCODER =
      new BinaryMessageEncoder<PaymentRequest>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentRequest> DECODER =
      new BinaryMessageDecoder<PaymentRequest>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentRequest> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentRequest> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentRequest> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentRequest>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PaymentRequest to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PaymentRequest from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentRequest instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentRequest fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence id;
  @Deprecated public java.lang.CharSequence payerAccountNumber;
  @Deprecated public java.lang.CharSequence receiverAccountNumber;
  @Deprecated public java.math.BigDecimal cashAmount;
  @Deprecated public long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentRequest() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param payerAccountNumber The new value for payerAccountNumber
   * @param receiverAccountNumber The new value for receiverAccountNumber
   * @param cashAmount The new value for cashAmount
   * @param timestamp The new value for timestamp
   */
  public PaymentRequest(java.lang.CharSequence id, java.lang.CharSequence payerAccountNumber, java.lang.CharSequence receiverAccountNumber, java.math.BigDecimal cashAmount, java.lang.Long timestamp) {
    this.id = id;
    this.payerAccountNumber = payerAccountNumber;
    this.receiverAccountNumber = receiverAccountNumber;
    this.cashAmount = cashAmount;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return payerAccountNumber;
    case 2: return receiverAccountNumber;
    case 3: return cashAmount;
    case 4: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: payerAccountNumber = (java.lang.CharSequence)value$; break;
    case 2: receiverAccountNumber = (java.lang.CharSequence)value$; break;
    case 3: cashAmount = (java.math.BigDecimal)value$; break;
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
   * Gets the value of the 'payerAccountNumber' field.
   * @return The value of the 'payerAccountNumber' field.
   */
  public java.lang.CharSequence getPayerAccountNumber() {
    return payerAccountNumber;
  }


  /**
   * Sets the value of the 'payerAccountNumber' field.
   * @param value the value to set.
   */
  public void setPayerAccountNumber(java.lang.CharSequence value) {
    this.payerAccountNumber = value;
  }

  /**
   * Gets the value of the 'receiverAccountNumber' field.
   * @return The value of the 'receiverAccountNumber' field.
   */
  public java.lang.CharSequence getReceiverAccountNumber() {
    return receiverAccountNumber;
  }


  /**
   * Sets the value of the 'receiverAccountNumber' field.
   * @param value the value to set.
   */
  public void setReceiverAccountNumber(java.lang.CharSequence value) {
    this.receiverAccountNumber = value;
  }

  /**
   * Gets the value of the 'cashAmount' field.
   * @return The value of the 'cashAmount' field.
   */
  public java.math.BigDecimal getCashAmount() {
    return cashAmount;
  }


  /**
   * Sets the value of the 'cashAmount' field.
   * @param value the value to set.
   */
  public void setCashAmount(java.math.BigDecimal value) {
    this.cashAmount = value;
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
   * Creates a new PaymentRequest RecordBuilder.
   * @return A new PaymentRequest RecordBuilder
   */
  public static kafka.bank.avro.PaymentRequest.Builder newBuilder() {
    return new kafka.bank.avro.PaymentRequest.Builder();
  }

  /**
   * Creates a new PaymentRequest RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentRequest RecordBuilder
   */
  public static kafka.bank.avro.PaymentRequest.Builder newBuilder(kafka.bank.avro.PaymentRequest.Builder other) {
    if (other == null) {
      return new kafka.bank.avro.PaymentRequest.Builder();
    } else {
      return new kafka.bank.avro.PaymentRequest.Builder(other);
    }
  }

  /**
   * Creates a new PaymentRequest RecordBuilder by copying an existing PaymentRequest instance.
   * @param other The existing instance to copy.
   * @return A new PaymentRequest RecordBuilder
   */
  public static kafka.bank.avro.PaymentRequest.Builder newBuilder(kafka.bank.avro.PaymentRequest other) {
    if (other == null) {
      return new kafka.bank.avro.PaymentRequest.Builder();
    } else {
      return new kafka.bank.avro.PaymentRequest.Builder(other);
    }
  }

  /**
   * RecordBuilder for PaymentRequest instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentRequest>
    implements org.apache.avro.data.RecordBuilder<PaymentRequest> {

    private java.lang.CharSequence id;
    private java.lang.CharSequence payerAccountNumber;
    private java.lang.CharSequence receiverAccountNumber;
    private java.math.BigDecimal cashAmount;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(kafka.bank.avro.PaymentRequest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.payerAccountNumber)) {
        this.payerAccountNumber = data().deepCopy(fields()[1].schema(), other.payerAccountNumber);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.receiverAccountNumber)) {
        this.receiverAccountNumber = data().deepCopy(fields()[2].schema(), other.receiverAccountNumber);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.cashAmount)) {
        this.cashAmount = data().deepCopy(fields()[3].schema(), other.cashAmount);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentRequest instance
     * @param other The existing instance to copy.
     */
    private Builder(kafka.bank.avro.PaymentRequest other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.payerAccountNumber)) {
        this.payerAccountNumber = data().deepCopy(fields()[1].schema(), other.payerAccountNumber);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.receiverAccountNumber)) {
        this.receiverAccountNumber = data().deepCopy(fields()[2].schema(), other.receiverAccountNumber);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.cashAmount)) {
        this.cashAmount = data().deepCopy(fields()[3].schema(), other.cashAmount);
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
    public kafka.bank.avro.PaymentRequest.Builder setId(java.lang.CharSequence value) {
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
    public kafka.bank.avro.PaymentRequest.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'payerAccountNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getPayerAccountNumber() {
      return payerAccountNumber;
    }


    /**
      * Sets the value of the 'payerAccountNumber' field.
      * @param value The value of 'payerAccountNumber'.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder setPayerAccountNumber(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.payerAccountNumber = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'payerAccountNumber' field has been set.
      * @return True if the 'payerAccountNumber' field has been set, false otherwise.
      */
    public boolean hasPayerAccountNumber() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'payerAccountNumber' field.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder clearPayerAccountNumber() {
      payerAccountNumber = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'receiverAccountNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getReceiverAccountNumber() {
      return receiverAccountNumber;
    }


    /**
      * Sets the value of the 'receiverAccountNumber' field.
      * @param value The value of 'receiverAccountNumber'.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder setReceiverAccountNumber(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.receiverAccountNumber = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'receiverAccountNumber' field has been set.
      * @return True if the 'receiverAccountNumber' field has been set, false otherwise.
      */
    public boolean hasReceiverAccountNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'receiverAccountNumber' field.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder clearReceiverAccountNumber() {
      receiverAccountNumber = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'cashAmount' field.
      * @return The value.
      */
    public java.math.BigDecimal getCashAmount() {
      return cashAmount;
    }


    /**
      * Sets the value of the 'cashAmount' field.
      * @param value The value of 'cashAmount'.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder setCashAmount(java.math.BigDecimal value) {
      validate(fields()[3], value);
      this.cashAmount = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'cashAmount' field has been set.
      * @return True if the 'cashAmount' field has been set, false otherwise.
      */
    public boolean hasCashAmount() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'cashAmount' field.
      * @return This builder.
      */
    public kafka.bank.avro.PaymentRequest.Builder clearCashAmount() {
      cashAmount = null;
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
    public kafka.bank.avro.PaymentRequest.Builder setTimestamp(long value) {
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
    public kafka.bank.avro.PaymentRequest.Builder clearTimestamp() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentRequest build() {
      try {
        PaymentRequest record = new PaymentRequest();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.payerAccountNumber = fieldSetFlags()[1] ? this.payerAccountNumber : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.receiverAccountNumber = fieldSetFlags()[2] ? this.receiverAccountNumber : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.cashAmount = fieldSetFlags()[3] ? this.cashAmount : (java.math.BigDecimal) defaultValue(fields()[3]);
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
  private static final org.apache.avro.io.DatumWriter<PaymentRequest>
    WRITER$ = (org.apache.avro.io.DatumWriter<PaymentRequest>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentRequest>
    READER$ = (org.apache.avro.io.DatumReader<PaymentRequest>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}









