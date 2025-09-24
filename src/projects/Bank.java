package projects;
//ask dusel about this, I thought i didn't need to import within the same project
import projects.bank.Account;

public class Bank {
    private Account[] accounts;

    public Bank() {
        accounts = new Account[100];
    }

    public boolean addAccount(Account account) {
        int slot = openSlot();
        if (slot != -1) {
            accounts[slot] = account;
            return true;
        }
        return false; //action incomplete
    }
    
    public void loadAccounts() {
        // Implementation to load accounts
        
    }

    private int openSlot() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                return i;
            }
        }
        return -1; // No open slots
    }
}
