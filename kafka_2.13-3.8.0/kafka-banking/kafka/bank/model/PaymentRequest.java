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

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "id=" + id +
                ", payerAccountNumber='" + payerAccountNumber + '\'' +
                ", receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", cashAmount=" + cashAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}