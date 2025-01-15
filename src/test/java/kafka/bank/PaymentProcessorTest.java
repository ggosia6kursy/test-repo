package kafka.bank;

import kafka.bank.model.PaymentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    PaymentProcessor paymentProcessor = Mockito.spy(new PaymentProcessor());

    public static List<PaymentRequest> invalidPayments() {
        return List.of(PaymentRequest.builder().id(UUID.randomUUID()).payerAccountNumber("333").build(), PaymentRequest.builder().id(UUID.randomUUID()).payerAccountNumber("1234567890").receiverAccountNumber("1234567890").cashAmount(new BigDecimal("300")).timestamp(System.currentTimeMillis()).build(), PaymentRequest.builder().id(UUID.randomUUID()).payerAccountNumber("0123456789").receiverAccountNumber("2345678910").cashAmount(new BigDecimal("1500")).timestamp(System.currentTimeMillis()).build());
    }

    public static List<PaymentRequest> correctPayments() {
        return List.of(PaymentRequest.builder().id(UUID.randomUUID()).payerAccountNumber("1234567890").receiverAccountNumber("2345678901").cashAmount(new BigDecimal("300")).timestamp(System.currentTimeMillis()).build(), PaymentRequest.builder().id(UUID.randomUUID()).payerAccountNumber("0123456789").receiverAccountNumber("2345678901").cashAmount(new BigDecimal("500")).timestamp(System.currentTimeMillis()).build());
    }

    @BeforeEach
    public void setUp(){
        doReturn(true).when(paymentProcessor).failRequest(any(PaymentRequest.class));
        doReturn(true).when(paymentProcessor).successRequest(any(PaymentRequest.class));
    }

    @ParameterizedTest
    @MethodSource("invalidPayments")
    @DisplayName("Invalid payment")
    void whenInvalidAccount_thenProcessingFailed(PaymentRequest invalidPaymentRequest) {
        boolean processingResult = paymentProcessor.processElement(invalidPaymentRequest);

        Assertions.assertFalse(processingResult);
    }

    @ParameterizedTest
    @MethodSource("correctPayments")
    @DisplayName("Correct payment")
    void whenCorrectAccount_thenProcessingSuccessful(PaymentRequest paymentRequest) {
        boolean processingResult = paymentProcessor.processElement(paymentRequest);

        Assertions.assertTrue(processingResult);
    }

}
