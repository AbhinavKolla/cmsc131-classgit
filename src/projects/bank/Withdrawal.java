package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount, Audit audit) {
        super(accountID, amount, audit);
    }

    @Override
    public void execute(Account account) {
        account.withdraw(super.getAmount());
    }

    @Override 
    public boolean validate(Account account) {
        if (account.getBalance() >= super.getAmount() && super.getAmount() > 0) {
            audit.recordTransaction(this, account);
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
