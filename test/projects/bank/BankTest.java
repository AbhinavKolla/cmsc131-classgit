package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        bank.loadAccounts(bank.FILE);
    }

    //im assuming that this is an acceptable way to test loading accounts and getCount at the same time.
    @Test // TODO failing
    void testLoadAccountsAndGetCount() {
        assertEquals(392, bank.getCount());
    }

    @Test
    void testAddAccount() {
        Account account = new Account("12346", "Billy Badass", AccountType.CHECKING, 10.0);
        boolean result = bank.addAccount(account);
        assertEquals(true, result);
    }

    @Test // TODO failing
    void testAddAccountDuplicateID() {
        Account duplicateAccount = new Account("wz240833", "Jane Doe", AccountType.SAVINGS, 500.0);
        boolean result = bank.addAccount(duplicateAccount);
        assertEquals(false, result);
    }

}