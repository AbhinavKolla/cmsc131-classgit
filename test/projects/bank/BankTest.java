package projects.bank;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    @Test
    void testLoadAccounts() {
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
        assertTrue(bank.getAccounts()[0].getAccountHolderName().equals(newBank.getAccounts()[0].getAccountHolderName()));
    }

    @Test
    void testLoadAccountsFailureMode() {
        Bank newBank = new Bank();
        boolean result = newBank.loadAccounts("invalid_file.csv");
        assertEquals(false, result);
    }
    
    @Test
    void testLoadAccountsReturnsTrueOnSuccess() {
        Bank newBank = new Bank();
        boolean result = newBank.loadAccounts(Bank.READ_FILE);
        assertEquals(true, result);
    }

    @Test
    void testWriteAccountsFailureMode() {
        Bank newBank = new Bank();
        boolean result = newBank.writeAccounts("fakeDir/test.txt");
        assertEquals(false, result);
    }

    @Test
    void testWriteAccountsReturnsTrueOnSuccess() {
        Bank newBank = new Bank();
        boolean result = newBank.writeAccounts(Bank.WRITE_FILE);
        assertEquals(true, result);
    }

    @Test
    void testWriteAccountsPreservesData() {
        Bank newBank = new Bank();
        newBank.loadAccounts(Bank.READ_FILE);
        newBank.writeAccounts(Bank.WRITE_FILE);
        assertArrayEquals(newBank.getAccounts(), newBank.getAccounts());
    }

     @Test
    void testProcessTransactionsFailure() {
        int result = bank.processTransactions("not/a/file.csv", Bank.AUDIT_FILE);
        assertEquals(result, 0);
    }

    @Test
    void testProcessTransactionsSuccess() {
        int result = bank.processTransactions("data/testtransactions.csv", Bank.AUDIT_FILE);
        assertEquals(result, 4);
    }

        // pass data/testtransactions.csv and check number of transactions processed
}

