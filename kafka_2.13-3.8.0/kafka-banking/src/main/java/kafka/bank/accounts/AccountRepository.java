package kafka.bank.accounts;

import java.util.List;
import java.util.Map;

public interface AccountRepository extends AccountListing {

    Account getAccount(String id);

    static AccountRepository getRepo() {
        return AccountRepositoryInMemory.getInstance();
    }

}

class AccountRepositoryInMemory implements AccountRepository {

    private final static AccountRepository REPO = new AccountRepositoryInMemory();

    private final Map<String, Account> accounts;

    private AccountRepositoryInMemory() {
        this.accounts = Map.of(
                "1234567890", new Account("1234567890"),
                "0123456789", new Account("0123456789"),
                "2345678901", new Account("2345678901"),
                "3456789012", new Account("3456789012"),
                "4567890123", new Account("4567890123"),
                "5678901234", new Account("5678901234"),
                "6789012345", new Account("6789012345"),
                "7890123456", new Account("7890123456")
        );
    }

    @Override
    public Account getAccount(String id) {
        if(id == null) return null;
        return accounts.get(id);
    }

    @Override
    public List<String> getAllAccountIds() {
        return List.copyOf(accounts.keySet());
    }

    static AccountRepository getInstance() {
        return REPO;
    }
}
