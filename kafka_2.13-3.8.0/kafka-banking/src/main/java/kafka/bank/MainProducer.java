package kafka.bank;

import kafka.bank.accounts.AccountListing;
import kafka.bank.infra.KafkaRequestSender;
import kafka.bank.payment.request.RequestSender;
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
        logger.info("Sending request");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("Press enter to send a message");
            String input = scanner.nextLine();
            paymentInitiator.initiateOperation();
        }
    }
}
