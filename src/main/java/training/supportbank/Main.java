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
                    String date = values[0];
                    String narrative = values[3];
                    BigDecimal amount = new BigDecimal(values[4]);

                    // create the transaction objects
                    Transaction theTransaction = new Transaction(fromAccountName, toAccountName, date, narrative, amount);
                    transactions.add(theTransaction);

                    // create the account objects
                    createAccountObject(accounts, fromAccountName, theTransaction);
                    accounts.get(fromAccountName).deductAmount(amount);

                    createAccountObject(accounts, toAccountName, theTransaction);
                    accounts.get(toAccountName).addAmount(amount);
                }
            }

            ArrayList<String> people = new ArrayList<>(accounts.keySet());
            // If you need a reminder of the names, uncomment the line below
            // System.out.println(people);

            Scanner newScan = new Scanner(System.in);
            System.out.println("Enter an account name or \"all\" to list all accounts");
            String command = newScan.nextLine();

            printListByUserInput(accounts, people, command);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printListByUserInput(Map<String, Account> accounts, ArrayList<String> people, String command) {
        if (command.toLowerCase().equals("all")) {
            accounts.forEach((k, v) -> System.out.println(k + "'s account is at " + v.getAmount() + " GBP"));
        } else if (people.contains(command)) {
            accounts.get(command).printTransactions();
        } else {
            System.out.println("Sorry, this is not a valid option. Please try again.");
        }
    }

    private static void createAccountObject(Map<String, Account> accounts, String accountName, Transaction theTransaction) {
        Account account = accounts.get(accountName);
        if (account == null) {
            account = new Account(accountName);
            accounts.put(accountName, account);
        }
        accounts.get(accountName).addTransaction(theTransaction);
    }
}
