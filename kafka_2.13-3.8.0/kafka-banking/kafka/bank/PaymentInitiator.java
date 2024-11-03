package kafka.bank;

import kafka.bank.model.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class PaymentInitiator {

    public static String MAIN_KAFKA_TOPIC_NAME = "payment-request";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    static Properties props = new Properties();

    static {
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        // We configure the serializer to describe the format in which we want to produce data into
        // our Kafka cluster
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static PaymentRequest generateRandomPaymentRequest() {
        // Make a copy of map keys set
        Set<String> keySet = new HashSet<>(Main.accounts.keySet());

        // Choose a payer account
        int randomIndex1 = new Random().nextInt(keySet.size());
        String[] keys = keySet.toArray(new String[0]);
        String payer = keys[randomIndex1];

        // Choose a receiver account
        keySet.remove(payer);
        String[] keys2 = keySet.toArray(new String[0]);
        int randomIndex2 = new Random().nextInt(keySet.size());
        String receiver = keys2[randomIndex2];

        // Generate random amount of cash (10-200) to be transferred
        int randomAmount = new Random().nextInt(190) + 10;

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(UUID.randomUUID());
        paymentRequest.setPayerAccountNumber(payer);
        paymentRequest.setReceiverAccountNumber(receiver);
        paymentRequest.setCashAmount(BigDecimal.valueOf(randomAmount));
        paymentRequest.setTimestamp(LocalDateTime.now());

        return paymentRequest;
    }

    public static void initiateOperation() {
        for (int i = 0; i < 5; i++) {
            PaymentRequest paymentRequest = generateRandomPaymentRequest();
            addPaymentRequestToKafkaTopic(paymentRequest);
        }
    }

    private static void addPaymentRequestToKafkaTopic(PaymentRequest paymentRequest) {
        Producer<String, String> producer = new KafkaProducer<>(props);
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<String, String>(MAIN_KAFKA_TOPIC_NAME, paymentRequest.getId().toString(), paymentRequest.toString()));
        try {
            RecordMetadata recordMetadata = sent.get();
            System.out.println(recordMetadata);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}