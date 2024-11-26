package kafka.bank.domain.payment.operationlog;

public interface OperationLogSender {

    void send(OperationLog status);
}

