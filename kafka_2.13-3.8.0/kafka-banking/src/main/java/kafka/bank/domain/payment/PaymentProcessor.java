package kafka.bank.domain.payment;

import kafka.bank.domain.account.AccountRepository;
import kafka.bank.domain.payment.operationlog.OperationLogSender;
import kafka.bank.domain.payment.request.PaymentRequest;

public interface PaymentProcessor {

    void process(PaymentRequest request);

    static PaymentProcessor getInstance(AccountRepository accountRepository, OperationLogSender operationLogSender) {
       return new PaymentProcessorImpl(accountRepository, operationLogSender);
    }
}
