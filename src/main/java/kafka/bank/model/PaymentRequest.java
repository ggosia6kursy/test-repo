package kafka.bank.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PaymentRequest {

    UUID id;

    String payerAccountNumber;

    String receiverAccountNumber;

    BigDecimal cashAmount;

    long timestamp;
}
