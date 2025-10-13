package projects.bank;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Bank {
    private Account[] accounts;
    private int openSlot;

    public final File FILE = new File("/workspaces/cmsc131-classgit-2/src/data/accounts.csv");
    public static void main(String[] args) {
        
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
        if (findID(account.getAccountID()) == -1 && openSlot != -1 && account != null) {
            accounts[openSlot] = account;

            //if openSlot is at end, find new empty slot, else increment
            if (openSlot == accounts.length - 1) {
                findEmptySlot();
            }
            else {
                openSlot++;
            }
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
    public void loadAccounts() {
        try {
            Scanner scanner = new Scanner(FILE);

            while(scanner.hasNext()) {
                String token = scanner.nextLine();
                System.out.println(token);
                this.addAccount(Account.createAccount(token));
                
            }
            scanner.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeAccounts(){
        try {
            FileWriter writer = new FileWriter(FILE);
            for (Account account : accounts){
                if (account != null) {
                    writer.write(account + "\n");
                }
            }
            writer.close();
        } 
        
        catch (IOException e) {
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
     */
    
     private void findEmptySlot() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                openSlot = i;
            }
        }
        openSlot = -1;
    }

}