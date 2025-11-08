package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        account.deposit(getAmount());
        audit.recordExecute(this, account);
    }

    @Override 
    public boolean validate(Account account, Audit audit) {
        return true;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.DEPOSIT;
    }

}
