package kafka.bank.payment.operationlog;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
public record OperationLog(UUID id, OperationType type, String requestId, String description, LocalDateTime timestamp) {

    public enum OperationType {
        SUCCESS,
        FAIL
    }

    public OperationLog {
        Objects.requireNonNull(id, "id is null");
    }
}
