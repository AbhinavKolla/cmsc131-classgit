package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.deposit(super.getAmount());
    }

    @Override 
    public boolean validate(Account account) {
        if(super.getAmount() < 0) {
            return false;
        }
        return true;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.DEPOSIT;
    }

}
