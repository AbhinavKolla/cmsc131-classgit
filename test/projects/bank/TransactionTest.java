package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;

    @BeforeEach
    void setUp() {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
    }

    @Test
    void testConstructorDataValidation() {
        assertThrows(IllegalArgumentException.class, 
        () -> {new Withdrawal(null, 0);}
        );
        // TODO check message
    }

    @Test
    void testMakeThrowsOnNullInput() {
        assertThrows(IllegalArgumentException.class, 
        () -> {Transaction.make(null);}
        );
        // TODO check message
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
        assertTrue(defaultDeposit.validate(ckgAccount));
        // TODO check validation failure
    }

    @Test
    void testValidateWithdrawal() {
        assertTrue(defaultWithdrawal.validate(ckgAccount));
        // TODO check validation failure
    }

    @Test
    void testExecuteDeposit() {
        assertDoesNotThrow(() -> defaultDeposit.execute(ckgAccount));
    }

    @Test
    void testExecuteWithdrawal() {
        assertDoesNotThrow(() -> defaultWithdrawal.execute(ckgAccount));
    }

    // TODO test execution changes balance as expected

}
