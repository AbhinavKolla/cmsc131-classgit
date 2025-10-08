package projects.bank;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Bank {
    private Account[] accounts;
    public final File FILE = new File("/workspaces/cmsc131-classgit-1/src/data/accounts.csv");
    public static void main(String[] args) {
        Bank bank = new Bank();



        bank.loadAccounts(bank.FILE);
    }

    public Bank() {
        accounts = new Account[500];
    }

    
    /*
        * Adds an account to the bank if there is an open slot.
        * Returns true if the account was added successfully, false otherwise.
        * @param account The account to be added.
        * @return boolean indicating success or failure.
    */
    public boolean addAccount(Account account) {
        // TODO add data validation
        int slot = findEmptySlot();
        if (findID(account.getAccountID()) == -1 && slot != -1) {
            accounts[slot] = account;
            return true;
        }

        return false; //action incomplete
    }

    /*
     * Returns the number of accounts currently in the bank.
     * @return The number of accounts.
     */
    public int getCount() {
        int count = 0;
        for (Account account : accounts) {
            if (account != null) {
                count++;
            }
        }
        return count;
    }

    /*
     * Loads accounts from the CSV file into the bank.
     */
    public void loadAccounts(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int index = 0;

            while(scanner.hasNext()) {
                String token = scanner.nextLine();
                System.out.println(token);
                // TODO use addAccount method here
                accounts[index] = Account.createAccount(token);
                index++;
            }
            scanner.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
        * Finds the index of an account by its ID.
        * @param id The ID of the account to find.
        * @return The index of the account if found, -1 otherwise.
    */
    public int findID(String id) {
        // TODO data validation
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getAccountID().equals(id)) {
                return i;
            }
        }
        return -1; // Not found
    }

    /* 
     * Finds the index of the first empty slot in the accounts array.
     * @return The index of the first empty slot, -1 if none are available.
     */
    // TODO consider maintaining this int as an instance variable, instead 
    // of iterating through accounts on each attempted add
     private int findEmptySlot() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                return i;
            }
        }
        return -1; // No empty slot found
    }

}