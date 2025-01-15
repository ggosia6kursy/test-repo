package kafka.bank;

import kafka.bank.mappers.OperationLogMapper;
import kafka.bank.mappers.PaymentRequestMapper;
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
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static kafka.bank.PaymentConstants.*;

public class PaymentProcessor {

    private static final PaymentRequestMapper paymentRequestMapper = new PaymentRequestMapper();
    private static final OperationLogMapper operationLogMapper = new OperationLogMapper();

    public boolean processElement(PaymentRequest paymentRequest) {
        System.out.println("Processing payment request: " + paymentRequest.getId().toString());
        // Validate if accounts exist
        boolean existsPayer = Main.accounts.containsKey(paymentRequest.getPayerAccountNumber());
        boolean existsReceiver = Main.accounts.containsKey(paymentRequest.getReceiverAccountNumber());
        if (!existsPayer || !existsReceiver) {
            failRequest(paymentRequest);  // Payment cannot be processed - invalid accounts
            return false;
        }
        // Validate if we have sufficient amount of cash
        if (Main.accounts.get(paymentRequest.getPayerAccountNumber()).compareTo(paymentRequest.getCashAmount()) < 0) {
            failRequest(paymentRequest); // Insufficient funds to perform operation
            return false;
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
            return true;
        }
        return false;
    }

    void processAll() {

        Consumer<String, kafka.bank.avro.PaymentRequest> consumer = new KafkaConsumer<>(PROCESSOR_PROPS);
        consumer.subscribe(List.of(REQUEST_KAFKA_TOPIC));

        while (true) {

            List<PaymentRequest> paymentRequests = getPaymentRequestFromKafkaTopic(consumer);
            for (PaymentRequest paymentRequest : paymentRequests) {
                processElement(paymentRequest);
            }
        }
    }

    private static List<PaymentRequest> getPaymentRequestFromKafkaTopic(Consumer<String, kafka.bank.avro.PaymentRequest> consumer) {
        List<PaymentRequest> paymentRequests = new ArrayList<>();
        ConsumerRecords<String, kafka.bank.avro.PaymentRequest> consumerRecords = consumer.poll(Duration.ofMinutes(1));
        consumerRecords.forEach(System.out::println);
        consumerRecords.forEach(r -> paymentRequests.add(paymentRequestMapper.fromAvroObject(r.value())));
        consumer.commitSync();
        return paymentRequests;
    }

    public boolean failRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLog.OperationType.FAIL);
        operationLog.setDescription("Payment request failed");
        operationLog.setRequestId(paymentRequest.getId().toString());
        operationLog.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        addOperationLogToKafkaTopic(operationLog);
        return true;
    }

    public boolean successRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = new OperationLog();
        operationLog.setType(OperationLog.OperationType.SUCCESS);
        operationLog.setDescription("Payment request succeeded");
        operationLog.setRequestId(paymentRequest.getId().toString());
        operationLog.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        addOperationLogToKafkaTopic(operationLog);
        return true;
    }

    public static void addOperationLogToKafkaTopic(OperationLog operationLog) {
        Producer<String, kafka.bank.avro.OperationLog> producer = new KafkaProducer<>(INITIATOR_PROPS);
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<>(OPERATION_LOG_KAFKA_TOPIC, operationLog.getId().toString(), operationLogMapper.toAvroObject(operationLog)));
        try {
            RecordMetadata recordMetadata = sent.get();
            System.out.println(recordMetadata + " - " + operationLog.getType());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
