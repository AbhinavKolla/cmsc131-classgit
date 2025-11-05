public class Audit{

    private FileWriter writer;
    
    /*
    * Constructor that takes in a filename to write audit logs to.
    * @param filename The name of the file to write audit logs to.
    * @throws IOException If there is an error opening the file.
    */
    public Audit(String filename) throws IOException{
        if(filename == null){
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        writer = new FileWriter(filename);
    }

    /*
    * Closes the audit log file.
    * @throws IOException If there is an error closing the file.
    */
    public void close(){
        try{
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    * Writes a string to the audit log file.
    * @param s The string to write to the audit log file.
    * @throws IOException If there is an error writing to the file.
    */
    private void write(String s){
        try{
            writer.write(s + System.lineSeparator());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    * Records a transaction with no such account error.
    * @param t The transaction to record.
    */
    public void recordNoSuchAccount(Transaction t){
        write(String.format("%s ERROR no such account: %s",
            Utils.timestamp(),
            t.toString();
        ));
    }

    /*
    * Records a transaction with insufficient funds error.
    * @param t The transaction to record.
    * @param a The account associated with the transaction.
    */
    public void recordInsufficientFunds(Transaction t, Account a){
        write(String.format("%s ERROR insufficient funds: %s, but account balance is %.2f",
            Utils.timestamp(),
            t.toString(),
            a.getBalance()
        ));
    }

    public void depositAmountNegative(Transaction t, Account a){
        write(String.format("%s ERROR deposit amount negative: %s, account balance is %.2f",
            Utils.timestamp(),
            t.toString(),
            a.getBalance()
        ));
    }

    /*
    * Records a successful transaction execution.
    * @param t The transaction to record.
    * @param a The account associated with the transaction.
    */
    public void recordExecute(Transactiont t, Account a){
        write(String.format("%s INFO: %s, account balance is now: %s",
            Utils.timestamp(),
            t.toString()
            a.getBalance()
        ));
    }
}