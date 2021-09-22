package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String args[]) {

        Map<String, Account> accounts = new HashMap<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        String csv = "Transactions2014.csv";
        String json = "Transactions2013.json";

        readFile(json, accounts, transactions);
        readFile(csv, accounts, transactions);

        printListByUserInput(accounts);
    }

    private static void printListByUserInput(Map<String, Account> accounts) {
        // If you need a reminder of the names, uncomment the line below
        // System.out.println(people);
        ArrayList<String> people = new ArrayList<>(accounts.keySet());
        Scanner newScan = new Scanner(System.in);
        System.out.println("Enter an account name or \"all\" to list all accounts");
        String command = newScan.nextLine();
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

    private static void readFile (String fileName, Map<String, Account> accounts, ArrayList<Transaction> transactions) {
        String regex = "(?<fileExtension>\\.(json|csv))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            if (matcher.group("fileExtension").equals(".json")) {
                System.out.println("json");
                readJsonFile(fileName, accounts, transactions);
            } else if (matcher.group("fileExtension").equals(".csv")) {
                System.out.println("csv");
                readCsvFile(fileName, accounts, transactions);
            }
        }
    }

    private static void readJsonFile (String fileName, Map<String, Account> accounts, ArrayList<Transaction> transactions) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            Transaction[] transactionsFromFile = gson.fromJson(br, Transaction[].class);
            List<Transaction> list = Arrays.asList(transactionsFromFile);
            for (Transaction transaction : list) {

                transactions.add(transaction);
                createAccountObjects(accounts, transaction.getFromAccount(), transaction.getToAccount(), transaction.getAmount(), transaction);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readCsvFile (String fileName, Map<String, Account> accounts, ArrayList<Transaction> transactions) {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (!values[1].equals("From")) {
                    String fromAccountName = values[1];
                    String toAccountName = values[2];
                    String date = values[0];
                    String narrative = values[3];
                    BigDecimal amount = new BigDecimal(values[4]);

                    // create the transaction objects
                    Transaction theTransaction = new Transaction(date, fromAccountName, toAccountName, narrative, amount);
                    transactions.add(theTransaction);

                    createAccountObjects(accounts, fromAccountName, toAccountName, amount, theTransaction);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createAccountObjects(Map<String, Account> accounts, String fromAccountName, String toAccountName, BigDecimal amount, Transaction theTransaction) {
        createAccountObject(accounts, fromAccountName, theTransaction);
        accounts.get(fromAccountName).deductAmount(amount);

        createAccountObject(accounts, toAccountName, theTransaction);
        accounts.get(toAccountName).addAmount(amount);
    }
}
