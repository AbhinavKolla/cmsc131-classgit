
package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        account.withdraw(getAmount());
        audit.recordExecute(this, account);
    }

    @Override 
    public boolean validate(Account account, Audit audit) {
        if (account.getBalance() >= getAmount()) {
            return true;
        }
        audit.recordInsufficientFunds(this, account);
        return false;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.WITHDRAWAL;
    }

}
