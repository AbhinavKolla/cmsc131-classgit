package projects.bank;

public class CheckingAccount extends Account{
    public CheckingAccount(String id, String name, double balance) {
        super(id, name, balance);
    }

    @Override
    public AccountType getAccountType(){
        return AccountType.CHECKING;
    }
}