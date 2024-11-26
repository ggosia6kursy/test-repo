package kafka.bank.payment;

import kafka.bank.accounts.AccountRepository;
import kafka.bank.payment.operationlog.OperationLogSender;
import kafka.bank.payment.request.PaymentRequest;

public interface PaymentProcessor {

    void process(PaymentRequest request);

    static PaymentProcessor getInstance(AccountRepository accountRepository, OperationLogSender operationLogSender) {
       return new PaymentProcessorImpl(accountRepository, operationLogSender);
    }
}
