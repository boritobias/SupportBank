package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
                    transactions.add(new Transaction(values[1], values[2], values[0], values[3], Double.parseDouble(values[4])));
                    accounts.put(values[1], new Account(values[1], 0));
                    accounts.get(values[1]).deductAmount(Double.parseDouble(values[4]));
                    accounts.get(values[1]).addTransaction(new Transaction(values[1], values[2], values[0], values[3], Double.parseDouble(values[4])));
                }
            }

//            for (Transaction transaction : transactions) {
//                System.out.println(transaction);
//            }

            // something does not work because the amount and transaction is overwritten
            accounts.forEach((k, v) -> System.out.println(k + ' ' + v));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
