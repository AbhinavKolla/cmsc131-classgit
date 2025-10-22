package projects.bank;

abstract class Account {
    private final String accountID;
    private final String accountHolderName;
    private double accountBalance;

    /**
     * Constructor to initialize an Account object.
     * @param id Unique identifier for the account.
     * @param name Name of the account holder.
     * @param balance Initial balance of the account.
     * @throws IllegalArgumentException if any parameter is null or invalid.
     */

    protected Account(String id, String name, double balance) {
        if (id == null) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        else if(name == null){
            throw new IllegalArgumentException("Account holder name cannot be null or empty.");
        }
        
        accountBalance = balance;
        accountID = id;
        accountHolderName = name;
    }

    public static Account createAccount(String token) {
        String[] parts = token.split(",");
        AccountType type = AccountType.valueOf(parts[0].toUpperCase());
        String name = parts[2];
        String id = parts[1];
        double balance = Double.parseDouble(parts[3]);

        if(type==CheckingAccount.CHECKING)
            return new CheckingAccount(id, name, balance);
        else if (type==CheckingAccount.SAVINGS)
            return new SavingAccount(id, name, balance);
    }

    public String toString(){
        return (toString(getAccountID), getAccountID(), getAccountHolderName(), getBalance());
    }

    // Accessors
    public String getAccountID() {
        return accountID;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return accountBalance;
    }

    abstract AccountType getAccountType()
}
    
