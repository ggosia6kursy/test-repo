package kafka.bank.domain.payment;

import kafka.bank.domain.account.Account;
import kafka.bank.domain.account.AccountRepository;
import kafka.bank.domain.payment.operationlog.OperationLog;
import kafka.bank.domain.payment.operationlog.OperationLogSender;
import kafka.bank.domain.payment.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
class PaymentProcessorImpl implements PaymentProcessor {

    private final AccountRepository accountRepository;

    private final OperationLogSender operationLogSender;

    private final BigDecimal totalAmount;

    public PaymentProcessorImpl(AccountRepository accountRepository, OperationLogSender operationLogSender) {
        this.accountRepository = accountRepository;
        this.operationLogSender = operationLogSender;
        this.totalAmount = getAllMoney(accountRepository);
    }

    private BigDecimal getAllMoney(AccountRepository accountRepository) {
        return accountRepository.getAllAccountIds().stream()
                .map(accountRepository::getAccount)
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void process(PaymentRequest paymentRequest) {
        log.info("Processing payment request: {}", paymentRequest);
        // Validate if accounts exist
        Account payerAccount = accountRepository.getAccount(paymentRequest.payerAccountNumber());
        Account recieverAccount = accountRepository.getAccount(paymentRequest.receiverAccountNumber());
        if (payerAccount == null || recieverAccount == null) {
            log.warn("Invalid account numbers");
            failRequest(paymentRequest);
            return;
        }
        // Validate if we have sufficient amount of cash
        BigDecimal totalBefore = recieverAccount.getBalance().add(payerAccount.getBalance());
        if (payerAccount.getBalance().compareTo(paymentRequest.cashAmount()) < 0) {
            log.warn("Invalid account balance to processed");
            failRequest(paymentRequest);
            return;// Insufficient funds to perform668 56
        }
        // Try to process payment
        payerAccount.withdraw(paymentRequest.cashAmount());
        recieverAccount.deposit(paymentRequest.cashAmount());

        BigDecimal currentTotalAmount = getAllMoney(accountRepository);
        // compare totalBefore and totalAfter
        if (totalAmount.compareTo(currentTotalAmount) != 0) {
            failRequest(paymentRequest);
            throw new IllegalStateException("Invalid amount of money in balance was " + totalAmount + currentTotalAmount);
        }
        successRequest(paymentRequest);
        accountRepository.getAllAccountIds().forEach(i -> log.info("Account Balance {}", accountRepository.getAccount(i)));

        // if everything is ok log success
    }

    private void failRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = OperationLog.builder()
                .id(UUID.randomUUID())
                .type(OperationLog.OperationType.FAIL)
                .description("Payment request failed")
                .requestId(paymentRequest.id().toString())
                .timestamp(LocalDateTime.now())
                .build();
        operationLogSender.send(operationLog);
    }

    private void successRequest(PaymentRequest paymentRequest) {
        OperationLog operationLog = OperationLog.builder()
                .id(UUID.randomUUID())
                .type(OperationLog.OperationType.SUCCESS)
                .description("Payment request succeeded")
                .requestId(paymentRequest.id().toString())
                .timestamp(LocalDateTime.now())
                .build();
        operationLogSender.send(operationLog);
    }

}
