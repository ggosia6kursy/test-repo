package kafka.bank.mappers;

import kafka.bank.model.PaymentRequest;

import java.util.UUID;

public class PaymentRequestMapper {

    public kafka.bank.avro.PaymentRequest toAvroObject(PaymentRequest paymentRequest) {
        return kafka.bank.avro.PaymentRequest.newBuilder()
                .setId(paymentRequest.getId().toString())
                .setPayerAccountNumber(paymentRequest.getPayerAccountNumber())
                .setReceiverAccountNumber(paymentRequest.getReceiverAccountNumber())
                .setCashAmount(paymentRequest.getCashAmount())
                .setTimestamp(paymentRequest.getTimestamp())
                .build();
    }

    public PaymentRequest fromAvroObject(kafka.bank.avro.PaymentRequest paymentRequest) {
        return PaymentRequest.builder()
                .id(UUID.fromString(String.valueOf(paymentRequest.getId())))
                .payerAccountNumber(String.valueOf(paymentRequest.getPayerAccountNumber()))
                .receiverAccountNumber(String.valueOf(paymentRequest.getReceiverAccountNumber()))
                .cashAmount(paymentRequest.getCashAmount())
                .timestamp(paymentRequest.getTimestamp())
                .build();
    }

}
