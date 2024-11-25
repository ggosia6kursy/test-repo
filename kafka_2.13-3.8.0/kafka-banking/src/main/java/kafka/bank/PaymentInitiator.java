package kafka.bank;

import com.google.gson.Gson;
import kafka.bank.model.PaymentRequest;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static kafka.bank.PaymentConstants.INITIATOR_PROPS;
import static kafka.bank.PaymentConstants.REQUEST_KAFKA_TOPIC;

public class PaymentInitiator {


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
        PaymentRequest paymentRequest = generateRandomPaymentRequest();
        addPaymentRequestToKafkaTopic(paymentRequest, REQUEST_KAFKA_TOPIC);
    }

    private static void addPaymentRequestToKafkaTopic(PaymentRequest paymentRequest, String kafkaTopicName) {
        Gson gson = new Gson();
        Producer<String, String> producer = new KafkaProducer<>(INITIATOR_PROPS);
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<>(kafkaTopicName, paymentRequest.getId().toString(), gson.toJson(paymentRequest)));
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
