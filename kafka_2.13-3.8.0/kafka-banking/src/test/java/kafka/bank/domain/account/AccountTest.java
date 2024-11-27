package kafka.bank.domain.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    public static Stream<Arguments> positiveTestData() {
        return Stream.of(
                Arguments.of(new BigDecimal(100), new BigDecimal(50)),
                Arguments.of(new BigDecimal("160.55"), new BigDecimal(100)),
                Arguments.of(new BigDecimal("999.99"), new BigDecimal(123)),
                Arguments.of(new BigDecimal(100), new BigDecimal("12.55")),
                Arguments.of(new BigDecimal(100), new BigDecimal("100"))
        );
    }

    public static Stream<Arguments> negativeTestData() {
        return Stream.of(
                Arguments.of(new BigDecimal(10), new BigDecimal(50)),
                Arguments.of(new BigDecimal("60.55"), new BigDecimal(100)),
                Arguments.of(new BigDecimal("99.99"), new BigDecimal(123))
        );
    }

    @DisplayName("Test for depose money on account")
    @ParameterizedTest(name = "Init account balance:  {0}, depose value {1}")
    @MethodSource("positiveTestData")
    void depositTest(BigDecimal initDeposit, BigDecimal addedAmount) {
        //given
        var systemUnderTest = new Account("testId", initDeposit);

        //when
        systemUnderTest.deposit(addedAmount);

        //then
        assertEquals(addedAmount.add(initDeposit).setScale(2, RoundingMode.HALF_UP), systemUnderTest.getBalance());
    }

    @DisplayName("Positive Test for withdraw money from account")
    @ParameterizedTest(name = "Init account balance:  {0}, withdraw value {1}")
    @MethodSource("positiveTestData")
    void withdrawTestPositive(BigDecimal initDeposit, BigDecimal widhdraw) {
        //given
        var systemUnderTest = new Account("testId", initDeposit);

        //when
        systemUnderTest.withdraw(widhdraw);

        //then
        assertEquals(initDeposit.subtract(widhdraw).setScale(2, RoundingMode.HALF_UP), systemUnderTest.getBalance());
    }

    @DisplayName("Negative Test for withdraw money from account (throw exception)")
    @ParameterizedTest(name = "Init account balance:  {0}, withdraw value {1}")
    @MethodSource("negativeTestData")
    void withdrawTestNegative(BigDecimal initDeposit, BigDecimal withdraw) {
        //given
        var systemUnderTest = new Account("testId", initDeposit);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> systemUnderTest.withdraw(withdraw));
    }

}
