package kafka.bank.domain.payment;

import kafka.bank.domain.account.Account;
import kafka.bank.domain.account.AccountRepository;
import kafka.bank.domain.payment.operationlog.OperationLog;
import kafka.bank.domain.payment.operationlog.OperationLogSender;
import kafka.bank.domain.payment.request.PaymentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class PaymentProcessorImplTest {

    @Test
    @DisplayName("Test transferring money between accounts - success")
    void testTransferMoneyBetweenAccounts() {
        //given
        List<OperationLog> operationLogs = new ArrayList<>();
        String accountId1 = "account1";
        String accountId2 = "account2";
        Account account1 = new Account(accountId1);
        account1.deposit(new BigDecimal(100));
        Account account2 = new Account(accountId2);
        account2.deposit(new BigDecimal(200));

        TestAccountRepository repository = new TestAccountRepository();
        repository.addAccount(account1);
        repository.addAccount(account2);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber(accountId2)
                .receiverAccountNumber(accountId1)
                .cashAmount(new BigDecimal(48))
                .timestamp(LocalDateTime.now())
                .build();
        OperationLogSender sender = operationLogs::add;
        PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);
        //when
        systemUnderTest.process(paymentRequest);

        //then
        Assertions.assertFalse(operationLogs.isEmpty());
        Assertions.assertEquals(1, operationLogs.size());
        Assertions.assertEquals(OperationLog.OperationType.SUCCESS, operationLogs.getFirst().type());
        Assertions.assertEquals(new BigDecimal("148.00"), account1.getBalance());
        Assertions.assertEquals(new BigDecimal("152.00"), account2.getBalance());
    }

    @Test
    @DisplayName("Test transferring money between accounts - not enough money")
    void testTransferMoneyBetweenAccountsNotEnoughMoney() {
        //given
        List<OperationLog> operationLogs = new ArrayList<>();
        String accountId1 = "account1";
        String accountId2 = "account2";
        Account account1 = new Account(accountId1);
        account1.deposit(new BigDecimal(100));
        Account account2 = new Account(accountId2);
        account2.deposit(new BigDecimal(200));

        TestAccountRepository repository = new TestAccountRepository();
        repository.addAccount(account1);
        repository.addAccount(account2);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber(accountId2)
                .receiverAccountNumber(accountId1)
                .cashAmount(new BigDecimal(500))
                .timestamp(LocalDateTime.now())
                .build();
        OperationLogSender sender = operationLogs::add;
        PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);
        //when
        systemUnderTest.process(paymentRequest);

        //then
        Assertions.assertFalse(operationLogs.isEmpty());
        Assertions.assertEquals(1, operationLogs.size());
        Assertions.assertEquals(OperationLog.OperationType.FAIL, operationLogs.getFirst().type());
        Assertions.assertEquals(new BigDecimal("100.00"), account1.getBalance());
        Assertions.assertEquals(new BigDecimal("200.00"), account2.getBalance());
    }

    @Test
    @DisplayName("Test transferring money between accounts - invalid payer account")
    void testTransferMoneyBetweenAccountsNoPayerAccount() {
        //given
        List<OperationLog> operationLogs = new ArrayList<>();
        String accountId1 = "account1";
        String accountId2 = "account2";
        Account account1 = new Account(accountId1);
        account1.deposit(new BigDecimal(100));
        Account account2 = new Account(accountId2);
        account2.deposit(new BigDecimal(200));

        TestAccountRepository repository = new TestAccountRepository();
        repository.addAccount(account1);
        repository.addAccount(account2);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber("notExitAccount")
                .receiverAccountNumber(accountId1)
                .cashAmount(new BigDecimal(40))
                .timestamp(LocalDateTime.now())
                .build();
        OperationLogSender sender = operationLogs::add;
        PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);
        //when
        systemUnderTest.process(paymentRequest);

        //then
        Assertions.assertFalse(operationLogs.isEmpty());
        Assertions.assertEquals(1, operationLogs.size());
        Assertions.assertEquals(OperationLog.OperationType.FAIL, operationLogs.getFirst().type());
        Assertions.assertEquals(new BigDecimal("100.00"), account1.getBalance());
        Assertions.assertEquals(new BigDecimal("200.00"), account2.getBalance());
    }

    @Test
    @DisplayName("Test transferring money between accounts - invalid receiver account")
    void testTransferMoneyBetweenAccountsNoReceiverAccount() {
        //given
        List<OperationLog> operationLogs = new ArrayList<>();
        String accountId1 = "account1";
        String accountId2 = "account2";
        Account account1 = new Account(accountId1);
        account1.deposit(new BigDecimal(100));
        Account account2 = new Account(accountId2);
        account2.deposit(new BigDecimal(200));

        TestAccountRepository repository = new TestAccountRepository();
        repository.addAccount(account1);
        repository.addAccount(account2);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber(accountId2)
                .receiverAccountNumber("noExitAccount")
                .cashAmount(new BigDecimal(40))
                .timestamp(LocalDateTime.now())
                .build();
        OperationLogSender sender = operationLogs::add;
        PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);
        //when
        systemUnderTest.process(paymentRequest);

        //then
        Assertions.assertFalse(operationLogs.isEmpty());
        Assertions.assertEquals(1, operationLogs.size());
        Assertions.assertEquals(OperationLog.OperationType.FAIL, operationLogs.getFirst().type());
        Assertions.assertEquals(new BigDecimal("100.00"), account1.getBalance());
        Assertions.assertEquals(new BigDecimal("200.00"), account2.getBalance());
    }

    @Test
    @DisplayName("Try to transferring total balance not match - exception")
    void tryTransferMoneyWhenMoneyNotMatch() {
        //given
        List<OperationLog> operationLogs = new ArrayList<>();
        String accountId1 = "account1";
        String accountId2 = "account2";
        Account account1 = new Account(accountId1);
        account1.deposit(new BigDecimal(100));
        Account account2 = new Account(accountId2);
        account2.deposit(new BigDecimal(200));

        TestAccountRepository repository = new TestAccountRepository();
        repository.addAccount(account1);
        repository.addAccount(account2);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber(accountId2)
                .receiverAccountNumber(accountId1)
                .cashAmount(new BigDecimal(40))
                .timestamp(LocalDateTime.now())
                .build();
        OperationLogSender sender = operationLogs::add;
        PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);
        //withdraw money outside of payment processor
        account1.withdraw(new BigDecimal(10));
        //when
        //then
        Assertions.assertThrows(IllegalStateException.class, () -> systemUnderTest.process(paymentRequest));
    }

    static class TestAccountRepository implements AccountRepository {

        List<Account> accounts = new ArrayList<Account>();

        @Override
        public Account getAccount(String id) {
            return accounts.stream()
                    .filter(account -> account.getAccountId().equals(id))
                    .findFirst().orElse(null);
        }

        @Override
        public List<String> getAllAccountIds() {
            return accounts.stream().map(Account::getAccountId).toList();
        }

        void addAccount(Account account) {
            accounts.add(account);
        }
    }

}
