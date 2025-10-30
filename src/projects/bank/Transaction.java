package projects.bank;

abstract class Transaction {

    private final String accountID;
    private final double amount;

    // abstract methods, to be overridden by subclasses
    
    /*
     * Executes the transaction on the given account.
     * @param account the account to execute the transaction on
     */
    abstract void execute(Account account);

    /*
     * Validates the transaction against the given account.
     * @param account the account to validate the transaction against
     * @return true if the transaction is valid, false otherwise
     */
    abstract boolean validate(Account account);

    /*
     * @param account the account to check
     * @return true if the account has sufficient funds, false otherwise
     */
    boolean hasSufficientFunds(Account account) {
        return account.getBalance() >= this.amount;
    }
    protected Transaction(String accountID, double amount) {
        if(accountID != null){
            this.accountID = accountID;
        } else {
            throw new IllegalArgumentException("Parameter accountID cannot be null.");
        }
        this.amount = amount;
    }

    /*
     * Creates a Transaction object from the given input line.
     * @param inputLine the input line to parse
     * @return a Transaction object
     */
    protected static Transaction make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("Parameter inputLine cannot be null.");
        }

        String[] tokens = inputLine.split(",");

        if(tokens[0].equals(TransactionType.WITHDRAWAL.name())){
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
