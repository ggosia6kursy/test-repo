package kafka.bank.infra;

import kafka.bank.PaymentConstants;
import kafka.bank.payment.PaymentProcessor;
import kafka.bank.payment.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;

@Slf4j
public class KafkaLoopConsumer implements Runnable {

    private final PaymentProcessor paymentProcessor;

    private final KafkaConsumer<String, PaymentRequest> consumer;

    public KafkaLoopConsumer(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
        consumer = buildConumer();
    }

    private KafkaConsumer<String, PaymentRequest> buildConumer() {
        return new KafkaConsumer<>(PaymentConstants.PROCESSOR_PROPS);
    }


    @Override
    public void run() {
        log.info("Starting consume messages");
        consumer.subscribe(List.of(PaymentConstants.REQUEST_KAFKA_TOPIC));
        while (true) {
            ConsumerRecords<String, PaymentRequest> records = consumer.poll(Duration.ofSeconds(1));
            log.info("Consumed {} records", records.count());
            records.forEach(record ->{
                log.info("Processing record {}", record.key());
                paymentProcessor.process(record.value());
            } );
            consumer.commitSync();

        }
    }
}
