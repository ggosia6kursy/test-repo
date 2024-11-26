package kafka.bank.payment.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
public record PaymentRequest(
        UUID id, String payerAccountNumber, String receiverAccountNumber, BigDecimal cashAmount,
        LocalDateTime timestamp
) {

    public PaymentRequest {
        Objects.requireNonNull(id, "Payment request id is null");
    }
}
