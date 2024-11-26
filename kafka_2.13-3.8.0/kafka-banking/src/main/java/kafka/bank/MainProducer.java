package kafka.bank;

import kafka.bank.domain.account.AccountListing;
import kafka.bank.domain.payment.PaymentInitiator;
import kafka.bank.domain.payment.request.RequestSender;
import kafka.bank.infra.KafkaRequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class MainProducer {


    private final static Logger logger = LoggerFactory.getLogger(MainProducer.class);


    public static void main(String[] args) throws InterruptedException {


        logger.info("Start");
        AccountListing repo = AccountListing.get();
        RequestSender sender = KafkaRequestSender.buildSender();
        PaymentInitiator paymentInitiator = new PaymentInitiator(repo, sender);
        Thread.sleep(1000);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("Press enter to send a message, or write \"exit\" to quit...");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            paymentInitiator.initiateOperation();
        }
    }
}
