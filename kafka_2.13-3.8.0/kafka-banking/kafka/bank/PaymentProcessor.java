package kafka.bank;

import kafka.bank.model.PaymentRequest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class PaymentProcessor {

    public static String MAIN_KAFKA_TOPIC_NAME = "payment-request";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    static Properties props = new Properties();

    static {
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "banking-group-earliest-2");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");
        // We configure the serializer to describe the format in which we want to produce data into
        // our Kafka cluster
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    static void process() {

        PaymentRequest paymentRequest = getPaymentRequestFromKafkaTopic();

        // Validate if accounts exist
        boolean existsPayer = Main.accounts.containsKey(paymentRequest.getPayerAccountNumber());
        boolean existsReceiver = Main.accounts.containsKey(paymentRequest.getReceiverAccountNumber());
        if (!existsPayer || !existsReceiver) {
            failRequest(paymentRequest);  // Payment cannot be processed - invalid accounts
        }
        // Validate if we have sufficient amount of cash
        if (Main.accounts.get(paymentRequest.getPayerAccountNumber()).compareTo(paymentRequest.getCashAmount()) < 0) {
            failRequest(paymentRequest); // Insufficient funds to perform668 56
        }
        // Try to process payment
        // Substract cash from payer account
        BigDecimal payerCash = Main.accounts.get(paymentRequest.getPayerAccountNumber());
        Main.accounts.put(paymentRequest.getPayerAccountNumber(), BigDecimal.valueOf(payerCash.doubleValue() - paymentRequest.getCashAmount().doubleValue()));
        // Add cash to receiver account
        BigDecimal receiverCash = Main.accounts.get(paymentRequest.getPayerAccountNumber());
        Main.accounts.put(paymentRequest.getReceiverAccountNumber(), BigDecimal.valueOf(receiverCash.doubleValue() + paymentRequest.getCashAmount().doubleValue()));
        // Check if operation went as expected
        BigDecimal totalBefore = BigDecimal.valueOf(payerCash.doubleValue() + receiverCash.doubleValue());
        BigDecimal totalAfter = BigDecimal.valueOf(Main.accounts.get(paymentRequest.getPayerAccountNumber()).doubleValue() + Main.accounts.get(paymentRequest.getReceiverAccountNumber()).doubleValue());
        // compare totalBefore and totalAfter
        if (totalAfter.equals(totalBefore)) {
            successRequest(paymentRequest);
        }
        // if everything is ok log success
    }

    private static PaymentRequest getPaymentRequestFromKafkaTopic() {
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of(MAIN_KAFKA_TOPIC_NAME));
        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMinutes(1));
        consumerRecords.forEach(System.out::println);
        consumer.commitSync();
        return null;
    }

    static void failRequest(PaymentRequest paymentRequest) {
        // here do actions related with failing request
    }

    static void successRequest(PaymentRequest paymentRequest) {
        // here do actions related with request success
    }

}