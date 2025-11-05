/** TODO list
 * move amount validation into constructor
 * don't need to reference super for inherited methods like getAmount
 */
package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.withdraw(super.getAmount());
    }

    @Override 
    public boolean validate(Account account) {
        if (account.getBalance() >= super.getAmount() && super.getAmount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.WITHDRAWAL;
    }

}
