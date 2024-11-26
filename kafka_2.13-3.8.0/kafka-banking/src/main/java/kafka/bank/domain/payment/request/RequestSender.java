package kafka.bank.domain.payment.request;

public interface RequestSender {

    void send(PaymentRequest request);

}

