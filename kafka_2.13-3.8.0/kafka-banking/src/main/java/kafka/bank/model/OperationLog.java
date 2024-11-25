package kafka.bank.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OperationLog {

    public enum OperationType {
        SUCCESS,
        FAIL
    }

    UUID id;

    OperationType type;

    String requestId;

    String description;

    LocalDateTime timestamp;
}
