package kafka.bank;

import kafka.bank.domain.account.AccountRepository;
import kafka.bank.domain.payment.PaymentProcessor;
import kafka.bank.domain.payment.operationlog.OperationLogSender;
import kafka.bank.infra.KafkaLoopConsumer;
import kafka.bank.infra.KafkaOperationLogSender;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MainConsumer {

  public static void main(String[] args) {

      log.info("Init consumer application");
      ExecutorService executor = Executors.newFixedThreadPool(3);
      AccountRepository accountRepository = AccountRepository.getRepo();
      OperationLogSender sender = KafkaOperationLogSender.buildSender();
      PaymentProcessor processor = PaymentProcessor.getInstance(accountRepository, sender);
      KafkaLoopConsumer kafkaLoopConsumer1 = new KafkaLoopConsumer(processor, "Consumer 1");
      KafkaLoopConsumer kafkaLoopConsumer2 = new KafkaLoopConsumer(processor, "Consumer 2");
      KafkaLoopConsumer kafkaLoopConsumer3 = new KafkaLoopConsumer(processor, "Consumer 3");
      executor.submit(kafkaLoopConsumer1);
      executor.submit(kafkaLoopConsumer2);
      executor.submit(kafkaLoopConsumer3);
  }

}
