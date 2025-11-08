package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;
    private Audit audit;

    @BeforeEach
    void setUp() throws Exception {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
        audit = new Audit("data/audit.txt");
    }

    @Test
    void testConstructorAccountIDValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class, 
            () -> {new Withdrawal(null, 0);}
        );
        assertEquals("Parameter accountID cannot be null.", e.getMessage());
    }

    @Test
    void testConstructorAmountValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class, 
            () -> {new Withdrawal("id", -1.0);}
        );
        assertEquals("Parameter amount cannot be negative.", e.getMessage());
    }

    @Test
    void testMakeThrowsOnNullInput() {
        Exception e = assertThrows(IllegalArgumentException.class, 
        () -> {Transaction.make(null);}
        );
        assertEquals("Parameter inputLine cannot be null.", e.getMessage());
    }


    @Test
    void testMakePreservesData() {
        String inputLine = "DEPOSIT,id,2.51";
        Transaction transaction = Transaction.make(inputLine);
        assertEquals(TransactionType.DEPOSIT, transaction.getTransactionType());
        assertEquals("id", transaction.getAccountID());
        assertEquals(2.51, transaction.getAmount(), 0.001);
    }

    @Test
    void testValidateDeposit() {
        assertTrue(defaultDeposit.validate(ckgAccount, audit));
        // TODO check validation failure: shouldn't this always return true?
    }

    @Test
    void testValidateWithdrawal() {
        assertTrue(defaultWithdrawal.validate(ckgAccount, audit));
    }

    @Test
    void testValidateWithdrawalFailure() {
        Account overdrawnAccount = new CheckingAccount("id", "owner", -1.0);
        assertFalse(defaultWithdrawal.validate(overdrawnAccount, audit));
    }

    @Test
    void testExecuteDeposit() {
        assertDoesNotThrow(() -> defaultDeposit.execute(ckgAccount, audit));
    }

    @Test
    void testExecuteWithdrawal() {
        assertDoesNotThrow(() -> defaultWithdrawal.execute(ckgAccount, audit));
    }

    @Test
    void testExecuteDepositChangesBalance() {
        double initialBalance = ckgAccount.getBalance();
        defaultDeposit.execute(ckgAccount, audit);
        assertEquals(initialBalance + defaultDeposit.getAmount(), ckgAccount.getBalance());
    }

}
