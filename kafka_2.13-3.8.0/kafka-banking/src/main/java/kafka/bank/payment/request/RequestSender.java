package kafka.bank.payment.request;

import kafka.bank.infra.KafkaRequestSender;

public interface RequestSender {

    void send(PaymentRequest request);

}

