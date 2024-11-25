package kafka.bank;

import com.google.gson.Gson;
import kafka.bank.model.OperationLog;
import kafka.bank.model.PaymentRequest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static kafka.bank.PaymentConstants.*;

public class PaymentProcessor {

    static void process() {

        List<PaymentRequest> paymentRequests = getPaymentRequestFromKafkaTopic();
        for (PaymentRequest paymentRequest : paymentRequests) {

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
    }

    private static List<PaymentRequest> getPaymentRequestFromKafkaTopic() {
        Gson gson = new Gson();
        List<PaymentRequest> paymentRequests = new ArrayList<>();
        Consumer<String, String> consumer = new KafkaConsumer<>(PROCESSOR_PROPS);
        consumer.subscribe(List.of(REQUEST_KAFKA_TOPIC));
        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMinutes(1));
        consumerRecords.forEach(System.out::println);
        consumerRecords.forEach(r -> paymentRequests.add(gson.fromJson(r.value(), PaymentRequest.class)));
        consumer.commitSync();
        return paymentRequests;
    }

    static void failRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLog.OperationType.FAIL);
        operationLog.setDescription("Payment request failed");
        operationLog.setRequestId(paymentRequest.getId().toString());
        operationLog.setTimestamp(LocalDateTime.now());
        addOperationLogToKafkaTopic(operationLog, OPERATION_LOG_KAFKA_TOPIC);
    }

    static void successRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLog.OperationType.SUCCESS);
        operationLog.setDescription("Payment request succeeded");
        operationLog.setRequestId(paymentRequest.getId().toString());
        operationLog.setTimestamp(LocalDateTime.now());
        addOperationLogToKafkaTopic(operationLog, OPERATION_LOG_KAFKA_TOPIC);
    }

    private static void addOperationLogToKafkaTopic(OperationLog operationLog, String kafkaTopicName) {
        Gson gson = new Gson();
        Producer<String, String> producer = new KafkaProducer<>(INITIATOR_PROPS);
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<>(kafkaTopicName, operationLog.getId().toString(), gson.toJson(operationLog)));
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
