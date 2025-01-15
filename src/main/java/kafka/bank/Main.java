package kafka.bank;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {

    protected static final Map<String, BigDecimal> accounts = new HashMap<>();

    static {
        accounts.putIfAbsent("0123456789", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("1234567890", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("2345678901", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("3456789012", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("4567890123", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("5678901234", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("6789012345", BigDecimal.valueOf(1000L));
        accounts.putIfAbsent("7890123456", BigDecimal.valueOf(1000L));
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            // Run producer
            Thread producerThread = new Thread(kafka.bank.PaymentInitiator::initiateOperation);
            producerThread.start();
            // Sleep for a while
            Thread.sleep(1000);
            // Run consumer
        }

        Thread consumerThread = new Thread();
        consumerThread.start();
    }
}
