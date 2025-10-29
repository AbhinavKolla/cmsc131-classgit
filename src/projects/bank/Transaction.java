package projects.bank;

abstract class Transaction {

    private final String accountID;
    private final double amount;

    // abstract methods, to be overridden by subclasses
    
    //TODO javadoc
    abstract void execute(Account account);
    
    // TODO javadoc
    abstract boolean validate(Account account);

    // concrete methods

    // TODO javadoc
    protected Transaction(String accountID, double amount) {
        this.accountID = accountID;
        this.amount = amount;
    }

    //TODO JAVADOC
    protected static Transaction make(String inputLine) {
        String[] tokens = inputLine.split();

        if(tokens[0] = TransactionType.WITHDRAWAL)
            return new Withdrawal(tokens[1], tokens[2]);
        else if(tokens[0] = TransactionType.DEPOSIT)
            return new Deposit(tokens[1], tokens[2]);
    }


    public String getAccountID() {
        return this.accountID;
    }



}
