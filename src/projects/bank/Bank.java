package projects.bank;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Bank {
    private Account[] accounts;
    private int openSlot;

    public static final String READ_FILE = ("data/accountsRead.csv");
    public static final String WRITE_FILE = ("data/accountsWrite.csv");
    public static final String AUDIT_FILE = ("data/audit.txt");

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

    /* Processes transactions from a CSV file.
     * @param fileName The name of the file to read from.
     * @return boolean indicating success or failure.
     */
    public int processTransactions(String fileName, String auditFileName){ 
        try {
            File file = new File(fileName);
            Audit audit = new Audit(auditFileName);
            Scanner scanner = new Scanner(file);
            int processedCount = 0;

            while(scanner.hasNext()) {
                Transaction trs = Transaction.make(scanner.nextLine());
                int targetIdx = this.findID(trs.getAccountID());

                if(targetIdx>=0){
                    Account target = accounts[targetIdx];
                    if(trs.validate(target, audit))
                        trs.execute(target, audit);
                        processedCount++;
                

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        audit.close();
        return processedCount;
        }
    }

    /* Loads accounts from a CSV file into the bank.
     * @param fileName The name of the file to read from.
     * @return boolean indicating success or failure.
     */
    public boolean loadAccounts(String fileName){
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                    String token = scanner.nextLine();
                    this.addAccount(Account.createAccount(token));
                    
                }
            scanner.close();
            return true;
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    /* Writes all accounts to a CSV file.
     * @param fileName The name of the file to write to.
     * @return boolean indicating success or failure.
     */
    public boolean writeAccounts(String fileName){
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            for (Account account : accounts){
                if (account != null) {
                    writer.write(account + System.lineSeparator());
                    writer.flush();
                }
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
        * Finds the index of an account by its ID.
        * @param id The ID of the account to find.
        * @return The index of the account if found, -1 otherwise.
    */
    public int findID(String id) {
        if(id==null){
            return -1;
        }
        else{
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i] != null && accounts[i].getAccountID().equals(id)) {
                    return i;
                }
            }
            return -1; // Not found
        }
    }

    /*
     * Finds the index of the first empty slot in the accounts array.
     */
    
     public void findEmptySlot() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                openSlot = i;
            }
        }
        openSlot = -1;
    }

    public Account[] getAccounts() {
        return accounts;
    }

}