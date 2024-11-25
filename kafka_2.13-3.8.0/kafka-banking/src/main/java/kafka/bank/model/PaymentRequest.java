package kafka.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentRequest {

    UUID id;

    String payerAccountNumber;

    String receiverAccountNumber;

    BigDecimal cashAmount;

    LocalDateTime timestamp;
}
