package kafka.bank.infra;

import kafka.bank.PaymentConstants;
import kafka.bank.payment.request.PaymentRequest;
import kafka.bank.payment.request.RequestSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static kafka.bank.PaymentConstants.INITIATOR_PROPS;

@Slf4j
public class KafkaRequestSender implements RequestSender {

    private final KafkaProducer<String, PaymentRequest> producer = buildProducer();

    public static RequestSender buildSender() {
        return new KafkaRequestSender();
    }

    private KafkaRequestSender() {

    }

    @Override
    public void send(PaymentRequest request) {
        Future<RecordMetadata> sent = producer.send(new ProducerRecord<>(PaymentConstants.REQUEST_KAFKA_TOPIC, request.id().toString(), request));
        try {
            RecordMetadata recordMetadata = sent.get();
            log.info("Successfully send {}", recordMetadata);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private KafkaProducer<String, PaymentRequest> buildProducer() {
        log.info("Creating producer...");
        KafkaProducer<String, PaymentRequest> objectObjectKafkaProducer = new KafkaProducer<>(INITIATOR_PROPS);
        System.out.println("Created producer...");
        return objectObjectKafkaProducer;
    }
}
