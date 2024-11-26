package kafka.bank.domain.account;

import java.util.List;

public interface AccountListing {

    List<String> getAllAccountIds();

    static AccountListing get() {
        return AccountRepositoryInMemory.getInstance();
    }
}
