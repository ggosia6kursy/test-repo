package kafka.bank;

import kafka.bank.accounts.AccountListing;
import kafka.bank.accounts.AccountRepository;
import kafka.bank.payment.request.PaymentRequest;
import kafka.bank.payment.request.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class PaymentInitiator {

    private static final Logger log = LoggerFactory.getLogger(PaymentInitiator.class);

    private final AccountListing accountRepository;

    private final RequestSender sender;

    public PaymentInitiator(AccountListing accountRepository, RequestSender sender) {
        this.accountRepository = accountRepository;
        this.sender = sender;
    }


    private PaymentRequest generateRandomPaymentRequest() {
        // Make a copy of map keys set
        List<String> allAccountIds = accountRepository.getAllAccountIds();


        // Choose a payer account
        Random random = new Random();
        int randomIndex1 = random.nextInt(allAccountIds.size());
        String payer = allAccountIds.get(randomIndex1);

        // Choose a receiver account
        int randomIndex2 = randomIndex1;

        while (randomIndex2 == randomIndex1) {
            randomIndex2 = random.nextInt(allAccountIds.size());
        }
        String receiver = allAccountIds.get(randomIndex2);

        // Generate random amount of cash (10-200) to be transferred
        int randomAmount = random.nextInt(190) + 10;

        return PaymentRequest.builder()
                .id(UUID.randomUUID())
                .payerAccountNumber(payer)
                .receiverAccountNumber(receiver)
                .cashAmount(BigDecimal.valueOf(randomAmount))
                .timestamp(LocalDateTime.now())
                .build();
    }

    public void initiateOperation() {
        PaymentRequest paymentRequest = generateRandomPaymentRequest();
        log.info("generated request {}", paymentRequest);
        sender.send(paymentRequest);
    }
}
