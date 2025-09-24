package projects.bank;

public class Account {
    private final String accountID;
    private final String accountHolderName;
    private final AccountType accountType;
    private double balance;

    //constructor
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
        balance = balance;
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
    
