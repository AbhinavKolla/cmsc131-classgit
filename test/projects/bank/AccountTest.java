package projects.bank;  // TODO correct package declartion

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
    void constructorThrowsForInvalidType(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new SavingAccount("w132", "Abhi", 0);}
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