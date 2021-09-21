package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class Main {
    public static void main(String args[]) {

        Map<String, Account> accounts = new HashMap<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        String path = "Transactions2014.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (!values[1].equals("From")) {
                    String fromAccountName = values[1];
                    String toAccountName = values[2];
                    BigDecimal amount = new BigDecimal(values[4]);

                    // create the transaction objects
                    Transaction theTransaction = new Transaction(fromAccountName, toAccountName, values[0], values[3], amount);
                    transactions.add(theTransaction);

                    // create the account objects
                    Account fromAccount = accounts.get(fromAccountName);
                    if (fromAccount == null) {
                        fromAccount = new Account(fromAccountName);
                        accounts.put(fromAccountName, fromAccount);
                    }
                    accounts.get(fromAccountName).addTransaction(theTransaction);

                    Account toAccount = accounts.get(toAccountName);
                    if (toAccount == null) {
                        toAccount = new Account(toAccountName);
                        accounts.put(toAccountName, toAccount);
                    }
                    accounts.get(toAccountName).addAmount(amount);
                }
            }

            accounts.forEach((k, v) -> System.out.println(v));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
