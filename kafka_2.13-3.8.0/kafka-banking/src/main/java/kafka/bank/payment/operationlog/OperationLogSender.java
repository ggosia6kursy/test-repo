package kafka.bank.payment.operationlog;

public interface OperationLogSender {

    void send(OperationLog status);
}

