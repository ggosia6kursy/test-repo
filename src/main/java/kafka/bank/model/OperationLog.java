package kafka.bank.model;

import lombok.Data;

import java.util.UUID;

@Data
public class OperationLog {

    public enum OperationType {
        ACCEPT,
        PROCESSING,
        SUCCESS,
        FAIL
    }

    UUID id = UUID.randomUUID();

    OperationType type;

    String requestId;

    String description;

    long timestamp;
}
