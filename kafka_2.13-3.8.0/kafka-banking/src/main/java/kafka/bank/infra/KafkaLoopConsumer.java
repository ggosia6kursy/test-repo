package kafka.bank.infra;

import kafka.bank.domain.payment.PaymentProcessor;
import kafka.bank.domain.payment.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;

@Slf4j
public class KafkaLoopConsumer implements Runnable {

    private final PaymentProcessor paymentProcessor;
    private final String name;

    private final KafkaConsumer<String, PaymentRequest> consumer;

    public KafkaLoopConsumer(PaymentProcessor paymentProcessor, String name) {
        this.paymentProcessor = paymentProcessor;
        this.name = name;
        consumer = buildConumer();
    }

    private KafkaConsumer<String, PaymentRequest> buildConumer() {
        return new KafkaConsumer<>(KafkaConfig.PROCESSOR_PROPS);
    }


    @Override
    public void run() {

        log.info("[{}] Starting consume messages", name);
        consumer.subscribe(List.of(KafkaConfig.REQUEST_KAFKA_TOPIC));
        while (true) {
            ConsumerRecords<String, PaymentRequest> records = consumer.poll(Duration.ofSeconds(1));
            log.info("[{}]Consumed {} records from partitions: {}", name, records.count(), records.partitions());
            records.forEach(record ->{
                log.info("[{}] Processing record {}", name,record.key());
                paymentProcessor.process(record.value());
            } );
            consumer.commitSync();
        }
    }
}
