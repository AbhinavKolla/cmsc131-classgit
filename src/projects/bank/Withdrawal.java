package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.withdraw(amount);
    }

    @Override 
    public boolean validate(Account account) {
        if (account.getBalance()<amount){
            return false;
        else if(accpimt.getBalance()>=amount)
            return true;
        }
    }

}
