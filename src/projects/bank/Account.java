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
        if(token == null){
            throw new IllegalArgumentException("Parameter token cannot be null.");
        }

        String[] parts = token.split(",");
        if(parts.length != 4){
            throw new IllegalArgumentException("Invalid token data.");
        }
        
        AccountType type = AccountType.valueOf(parts[0].toUpperCase());
        String name = parts[2];
        String id = parts[1];
        double balance = Double.parseDouble(parts[3]); 

        if(type==AccountType.CHECKING)
            return new CheckingAccount(id, name, balance);
        else
            return new SavingAccount(id, name, balance);
    }

    public void deposit(double amount){
        this.accountBalance += amount;
    }

    public void withdraw(double amount){
        this.accountBalance -= amount;
    }

    public String toString(){
        return String.format("%s,%s,%s,%f", getAccountType().name(), getAccountID(), getAccountHolderName(), getBalance());
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

    abstract AccountType getAccountType();
}
    
