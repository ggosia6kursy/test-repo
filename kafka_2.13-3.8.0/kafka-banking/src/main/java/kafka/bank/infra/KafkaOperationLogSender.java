package kafka.bank.infra;

import kafka.bank.PaymentConstants;
import kafka.bank.payment.operationlog.OperationLog;
import kafka.bank.payment.operationlog.OperationLogSender;
import kafka.bank.payment.request.RequestSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static kafka.bank.PaymentConstants.INITIATOR_PROPS;

@Slf4j
public class KafkaOperationLogSender implements OperationLogSender {

    private final KafkaProducer<String, OperationLog> producer = buildProducer();

    public static OperationLogSender buildSender() {
        return new KafkaOperationLogSender();
    }

    private KafkaOperationLogSender() {

    }

    @Override
    public void send(OperationLog request) {
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<>(PaymentConstants.OPERATION_LOG_KAFKA_TOPIC, request.id().toString(), request));
        try {
            RecordMetadata recordMetadata = sent.get();
            log.info("Successfully send {}", recordMetadata);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private KafkaProducer<String, OperationLog> buildProducer() {
        log.info("Creating producer...");
        KafkaProducer<String, OperationLog> producer = new KafkaProducer<>(INITIATOR_PROPS);
        System.out.println("Created producer...");
        return producer;
    }
}
