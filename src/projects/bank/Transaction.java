package projects.bank;

abstract class Transaction {

    private final String accountID;
    private final double amount;

    // abstract methods, to be overridden by subclasses
    
    /*
     * Executes the transaction on the given account.
     * @param account the account to execute the transaction on
     */
    abstract void execute(Account account, Audit audit);

    /*
     * Validates the transaction against the given account.
     * @param account the account to validate the transaction against
     * @return true if the transaction is valid, false otherwise
     */
    abstract boolean validate(Account account, Audit audit);

    /*
     * @param account the account to check
     * @return true if the account has sufficient funds, false otherwise
     */
    boolean hasSufficientFunds(Account account) {
        return account.getBalance() >= this.amount;
    }

    /*
     * Constructor for Transaction class.
     * @param accountID the ID of the account associated with the transaction
     * @param amount the amount of the transaction
     */
    protected Transaction(String accountID, double amount) {
        if(accountID == null){
            throw new IllegalArgumentException("Parameter accountID cannot be null.");
        } else if(amount < 0){
            throw new IllegalArgumentException("Parameter amount cannot be negative.");
        }

        this.accountID = accountID;
        this.amount = amount;
        
    }

    /*
     * Creates a Transaction object from the given input line.
     * @param inputLine the input line to parse
     * @return a Transaction object
     */
    public static Transaction make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("Parameter inputLine cannot be null.");
        }

        String[] tokens = inputLine.split(",");

        if(TransactionType.valueOf(tokens[0].toUpperCase()).equals(TransactionType.WITHDRAWAL)){
            return new Withdrawal(tokens[1], Double.valueOf(tokens[2]));
        } else {
            return new Deposit(tokens[1], Double.valueOf(tokens[2]));
        }
    }


    public String getAccountID() {
        return this.accountID;
    }

    public double getAmount() {
        return this.amount;
    }

    /*
     * @return the type of the transaction
     */
    abstract TransactionType getTransactionType();

}
