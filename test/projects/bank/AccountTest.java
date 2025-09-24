package projects.account;

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
            () -> {new Account(null, "Abhi", "SAVINGS", 0);}
        );
        assertEquals(
            "Account ID cannot be null or empty.", 
            exception.getMessage()
        );
    }

    void constructorThrowsForInvalidName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("w132", null, "SAVINGS", 0);}
        );
        assertEquals(
            "Account holder name cannot be null or empty.", 
            exception.getMessage()
        );
    }

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
}