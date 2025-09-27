package projects.bank;

public class Bank {
    private Account[] accounts;

    public Bank() {
        accounts = new Account[100];
    }

    // TODO add javadoc
    public boolean addAccount(Account account) {
        // TODO openSlot undefined
        int slot = openSlot(null);
        if (slot != -1) {
            accounts[slot] = account;
            return true;
        }
        return false; //action incomplete
    }
    
    public void loadAccounts() {
        // Implementation to load accounts
        
    }

    // TODO add javadoc
    private int findID(String id) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == id) {   // TODO fix comparison
                return i;
            }
        }
        return -1; // Not found
    }
}
