package kafka.bank;

import java.util.Properties;

public class PaymentConstants {

    // Kafka Topics
    public static final String REQUEST_KAFKA_TOPIC = "payment-request";
    public static final String OPERATION_LOG_KAFKA_TOPIC = "operation-log";

    // Server/Client Settings
    public static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static final Properties PROCESSOR_PROPS = new Properties();

    static {
        PROCESSOR_PROPS.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        PROCESSOR_PROPS.put("group.id", "banking-group-earliest");
        PROCESSOR_PROPS.put("auto.offset.reset", "earliest");
        //PROCESSOR_PROPS.put("enable.auto.commit", "true");
        PROCESSOR_PROPS.put("max.poll.records","1");
        PROCESSOR_PROPS.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        PROCESSOR_PROPS.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static final Properties INITIATOR_PROPS = new Properties();

    static {
        INITIATOR_PROPS.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        INITIATOR_PROPS.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        INITIATOR_PROPS.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }
}
