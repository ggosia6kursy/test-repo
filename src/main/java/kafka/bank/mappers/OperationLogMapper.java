package kafka.bank.mappers;

import kafka.bank.avro.OperationType;
import kafka.bank.model.OperationLog;

public class OperationLogMapper {

    public kafka.bank.avro.OperationLog toAvroObject(final OperationLog operationLog) {
        return kafka.bank.avro.OperationLog.newBuilder()
                .setType(toAvroObject(operationLog.getType()))
                .setRequestId(operationLog.getRequestId())
                .setDescription(operationLog.getDescription())
                .setTimestamp(operationLog.getTimestamp())
                .build();
    }

    private OperationType toAvroObject(final OperationLog.OperationType type) {
        return OperationType.valueOf(type.name());
    }
}
