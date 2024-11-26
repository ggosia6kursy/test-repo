package kafka.bank.accounts;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Account {

    private final String accountId;

    private BigDecimal balance = new BigDecimal(100).setScale(2, RoundingMode.HALF_UP);

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public synchronized void withdraw(BigDecimal amount) {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance can't be negative");
        }
        this.balance = balance.subtract(amount);
    }

    public synchronized void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
