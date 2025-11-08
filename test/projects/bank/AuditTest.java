package projects.bank;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuditTest {
    private Audit audit;

    @BeforeEach
    void setUp() throws Exception {
        audit = new Audit(Bank.AUDIT_FILE);
    }

    @Test
    void testRecordNoSuchAccount() {
        Transaction t = new Withdrawal("no_such_account", 100.0);
        audit.recordNoSuchAccount(t);
        audit.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(Bank.AUDIT_FILE));
            String line = scan.nextLine();

            assertEquals(
                true,
                line.contains("ERROR no such account")
            );

            assertFalse(scan.hasNextLine());

            scan.close();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Test
    void testRecordInsufficientFunds() {
        Account a = new CheckingAccount("account1", "reze", 500.0);
        Transaction t = new Withdrawal("account1", 1000.0);
        audit.recordInsufficientFunds(t, a);
        audit.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(Bank.AUDIT_FILE));
            String line = scan.nextLine();

            assertEquals(
                true,
                line.contains("ERROR insufficient funds")
            );

            assertFalse(scan.hasNextLine());

            scan.close();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Test
    void testRecordExecute() {
        Account a = new CheckingAccount("account1", "reze", 500.0);
        Transaction t = new Deposit("account1", 1000.0);
        audit.recordExecute(t, a);
        audit.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(Bank.AUDIT_FILE));
            String line = scan.nextLine();

            assertEquals(
                true,
                line.contains("account balance is now:")
            );

            assertFalse(scan.hasNextLine());

            scan.close();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }


}
