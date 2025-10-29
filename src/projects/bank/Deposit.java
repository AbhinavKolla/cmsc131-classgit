package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.deposit(amount);
    }

    @Override 
    public boolean validate(Account account) {
        return true;
    }

}
