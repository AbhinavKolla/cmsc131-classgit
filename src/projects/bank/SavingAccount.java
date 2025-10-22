public class SavingAccount extends Account{
    public SavingAccount(String id, String name, double balance) {
        super(id, name, balance);
    }

    @Override
    public AccountType getAccountType(){
        return AccountType.SAVINGS;
    }
}