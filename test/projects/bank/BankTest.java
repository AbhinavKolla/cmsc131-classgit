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
        Account account = new SavingAccount("12346", "Billy Badass", 10.0);
        boolean result = bank.addAccount(account);
        assertEquals(true, result);
    }

    @Test
    void testAddAccountDuplicateID() {
        Account duplicateAccount = new SavingAccount("wz240833", "Jane Doe", 500.0);
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
        Account account = new SavingAccount("12347", "John Smith", 200.0);
        bank.addAccount(account);
        bank.writeAccounts(Bank.WRITE_FILE);
        Bank newBank = new Bank();
        newBank.loadAccounts(Bank.WRITE_FILE);
        assertEquals(bank.getCount(), newBank.getCount());
        // TODO check accounts in bank and newBank are the same
    }

    @Test
    void testLoadAccountsFailureMode() {
        Bank newBank = new Bank();
        boolean result = newBank.loadAccounts("invalid_file.csv");
        assertEquals(false, result);
    }
    
    // TODO loadAccounts preserves data
    @Test
    void testLoadAccountsPreservesData() {
        Bank newBank = new Bank();
        newBank.loadAccounts(Bank.READ_FILE);
        assertEquals(bank.getAccounts(), newBank.getAccounts());
        
    }
    // TODO loadAccounts returns true on succeed
    // TODO writeAccounts failure mode
    // TODO writeAccounts returns true on succeed
    // TODO writeAccounts preserves data

}