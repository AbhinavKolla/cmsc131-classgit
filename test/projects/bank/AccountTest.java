package projects.bank;  // TODO correct package declartion

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest{
    private Account account;

    @BeforeEach
    void setUp(){
        account = new Account("w132", "Abhi", AccountType.SAVINGS, 0);
    }

    @Test
    void constructorThrowsForInvalidID() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account(null, "Abhi", AccountType.SAVINGS, 0);} // TODO use enum for account type
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
            () -> {new Account("w132", null, AccountType.SAVINGS, 0);} // TODO use enum for account type
        );
        assertEquals(
            "Account holder name cannot be null or empty.", 
            exception.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvalidType(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("w132", "Abhi", null, 0);}
        );
        assertEquals(
            "Account type cannot be null or empty.", 
            exception.getMessage()
        );
    }

    // TODO 
    // static factory throws on null input
    // static factory preserves data
    // static factor failure mode
    // toCSV output is correct
}