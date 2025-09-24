package projects.bank;

public class Account {
    private final String accountID;
    private final String accountHolderName;
    private final AccountType accountType;
    private double balance;

    //constructor
    public Account(String id, String name, AccountType type, double balance) {
        this.accountID = id;
        this.accountHolderName = name;
        this.accountType = type;
        this.balance = balance;
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
        return balance;
    }
}
    
