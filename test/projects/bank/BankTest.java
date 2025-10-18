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
        bank.loadAccounts(Bank.READ_FILE);
    }

    //im assuming that this is an acceptable way to test loading accounts and getCount at the same time.
    @Test
    void testLoadAccountsAndGetCount() {
        assertEquals(392, bank.getCount());
    }

    @Test
    void testAddAccount() {
        Account account = new Account("12346", "Billy Badass", AccountType.CHECKING, 10.0);
        boolean result = bank.addAccount(account);
        assertEquals(true, result);
    }

    @Test
    void testAddAccountDuplicateID() {
        Account duplicateAccount = new Account("wz240833", "Jane Doe", AccountType.SAVINGS, 500.0);
        boolean result = bank.addAccount(duplicateAccount);
        assertEquals(false, result);
    }

    @Test 
    void testFindID() {
        int index = bank.findID("wz240833");
        assertEquals(0, index); 
    }

    @Test 
    void testNullFindID() {
        int index = bank.findID(null);
        assertEquals(-1, index); 
    }

    @Test
    void testWriteAccounts() {
        Account account = new Account("12347", "John Smith", AccountType.SAVINGS, 200.0);
        bank.addAccount(account);
        bank.writeAccounts(Bank.WRITE_FILE);
        Bank newBank = new Bank();
        newBank.loadAccounts(Bank.WRITE_FILE);
        assertEquals(bank.getCount(), newBank.getCount());
        // TODO check accounts in bank and newBank are the same
    }

    // TODO loadAccounts failure mode
    // TODO loadAccounts preserves data
    // TODO loadAccounts returns true on succeed
    // TODO writeAccounts failure mode
    // TODO writeAccounts returns true on succeed
    // TODO writeAccounts preserves data

}