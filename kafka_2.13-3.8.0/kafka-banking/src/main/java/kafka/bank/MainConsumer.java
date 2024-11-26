package kafka.bank;

import kafka.bank.domain.account.AccountRepository;
import kafka.bank.infra.KafkaLoopConsumer;
import kafka.bank.infra.KafkaOperationLogSender;
import kafka.bank.domain.payment.PaymentProcessor;
import kafka.bank.domain.payment.operationlog.OperationLogSender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainConsumer {

  public static void main(String[] args) {

      log.info("Init consumer application");
      AccountRepository accountRepository = AccountRepository.getRepo();
      OperationLogSender sender = KafkaOperationLogSender.buildSender();
      PaymentProcessor processor = PaymentProcessor.getInstance(accountRepository, sender);
      KafkaLoopConsumer kafkaLoopConsumer = new KafkaLoopConsumer(processor);
      kafkaLoopConsumer.run();

  }

}
