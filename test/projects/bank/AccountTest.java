/** TODO list
 * 
 * test deposit method
 * 
 * test withdrawal method
 */
package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest{
    private Account account;

    @BeforeEach
    void setupAccount() {
        account = new SavingAccount(
            "wz240833",
            "Anna Gomez",
            8111.00
        );
    }

    @Test
    void constructorThrowsForInvalidID() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new SavingAccount(null, "Abhi", 0);}
        );
        assertEquals(
            "Account ID cannot be null or empty.", 
            exception.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvalidName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new SavingAccount("w132", null, 0);}
        );
        assertEquals(
            "Account holder name cannot be null or empty.", 
            exception.getMessage()
        );
    }

    
    @Test
    void staticFactoryThrowsOnNullInput() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {Account.createAccount(null);}
        );
        assertEquals(
            "Parameter token cannot be null.",
            exception.getMessage()
        );
    }

    // static factory preserves data
    @Test
    void staticFactoryPreservesData() {
        String token = "checking,wz240833,Anna Gomez,8111.00";
        Account account = Account.createAccount(token);
        assertEquals(AccountType.CHECKING, account.getAccountType());
        assertEquals("wz240833", account.getAccountID());
        assertEquals("Anna Gomez", account.getAccountHolderName());
        assertEquals(8111.00, account.getBalance(), 0.001);
    }

    // static factory failure mode
    @Test
    void staticFactoryThrowsOnInvalidInput() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {Account.createAccount("invalid_token");}
        );
        assertEquals(
            "Invalid token data.",
            exception.getMessage()
        );
    }

    @Test
    void toStringProducesCorrectOutput() {
        String expected = "SAVINGS,wz240833,Anna Gomez,8111.000000";
        String actual = account.toString();
        assertEquals(expected, actual);
    }
}