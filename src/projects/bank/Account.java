package projects.bank;

public class Account {
    private final String accountID;
    private final String accountHolderName;
    private final AccountType accountType;
    private double accountBalance;

    /**
     * Constructor to initialize an Account object.
     * @param id Unique identifier for the account.
     * @param name Name of the account holder.
     * @param type Type of the account (e.g., SAVINGS, CHECKING).
     * @param balance Initial balance of the account.
     * @throws IllegalArgumentException if any parameter is null or invalid.
     */

    public Account(String id, String name, AccountType type, double balance) {
        if (id == null) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        else if(name == null){
            throw new IllegalArgumentException("Account holder name cannot be null or empty.");
        }
        else if(type == null){
            throw new IllegalArgumentException("Account type cannot be null or empty.");
        }
        accountID = id;
        accountHolderName = name;
        accountType = type;
        accountBalance = balance; 
    }

    // TODO javadoc
    //factory
    public static Account createAccount(String token) {
        String[] parts = token.split(",");
        AccountType type = AccountType.valueOf(parts[0].toUpperCase());
        String name = parts[2];
        String id = parts[1];
        double balance = Double.parseDouble(parts[3]);
        return new Account(id, name, type, balance);
    }

    public String toString() {
        return accountType + "," + accountID + "," + accountHolderName + "," + accountBalance;
    }

    // Accessors
    public String getAccountID() {
        return accountID;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return accountBalance;
    }
}
    
