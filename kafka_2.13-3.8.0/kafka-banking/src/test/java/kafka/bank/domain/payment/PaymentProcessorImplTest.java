package kafka.bank.domain.payment;

import kafka.bank.domain.account.Account;
import kafka.bank.domain.account.AccountRepository;
import kafka.bank.domain.payment.operationlog.OperationLog;
import kafka.bank.domain.payment.operationlog.OperationLogSender;

import java.util.List;

class PaymentProcessorImplTest {

    AccountRepository repository = new AccountRepository() {
        @Override
        public Account getAccount(String id) {
            return null;
        }

        @Override
        public List<String> getAllAccountIds() {
            return List.of();
        }
    };

    OperationLogSender sender = new OperationLogSender() {
        @Override
        public void send(OperationLog request) {

        }
    };


    private final PaymentProcessorImpl systemUnderTest = new PaymentProcessorImpl(repository, sender);

}
