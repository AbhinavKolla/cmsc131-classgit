/** TODO LIST
 * move amount validation logic into constructor, just return true
 * don't need to reference super for inherited methods like getAmount
 */
package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.deposit(super.getAmount());
        audit.recordTransaction(this, account);
    }

    @Override 
    public boolean validate(Account account) {
        if(super.getAmount() < 0) {
            audit.depositAmountNegative(this, account);
            return false;
        }
        return true;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.DEPOSIT;
    }

}
