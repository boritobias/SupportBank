package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private Map<String, Account> accounts;
    private ArrayList<Transaction> transactions;

    public FileReader(Map<String, Account> initAccounts, ArrayList<Transaction> initTransactions) {
        accounts = initAccounts;
        transactions = initTransactions;
    }

    public void readFile (String fileName) {
        String regex = "(?<fileExtension>\\.(json|csv))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            if (matcher.group("fileExtension").equals(".json")) {
                readJsonFile(fileName);
            } else if (matcher.group("fileExtension").equals(".csv")) {
                readCsvFile(fileName);
            }
        }
    }

    private void readJsonFile (String fileName) {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString())
        );
        Gson gson = gsonBuilder.create();

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));

            Transaction[] transactionsFromFile = gson.fromJson(br, Transaction[].class);
            List<Transaction> list = Arrays.asList(transactionsFromFile);
            for (Transaction transaction : list) {

                transactions.add(transaction);
                createAccountObjects(accounts, transaction.getFromAccount(), transaction.getToAccount(), transaction.getAmount(), transaction);
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file does not exist. Please try again.");
            // e.printStackTrace();
        }
    }

    private void readCsvFile (String fileName) {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (!values[1].equals("From")) {
                    String fromAccountName = values[1];
                    String toAccountName = values[2];
                    ArrayList<Integer> dateConvertToLocalDate = new ArrayList<>();
                    for (String s : values[0].split("/")) {
                        dateConvertToLocalDate.add(Integer.parseInt(s));
                    }
                    LocalDate date = LocalDate.of(dateConvertToLocalDate.get(2), dateConvertToLocalDate.get(1), dateConvertToLocalDate.get(0));
                    String narrative = values[3];
                    BigDecimal amount = new BigDecimal(values[4]);

                    // create the transaction objects
                    Transaction theTransaction = new Transaction(date, fromAccountName, toAccountName, narrative, amount);
                    transactions.add(theTransaction);

                    createAccountObjects(accounts, fromAccountName, toAccountName, amount, theTransaction);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file does not exist. Please try again.");
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAccountObjects(Map<String, Account> accounts, String fromAccountName, String toAccountName, BigDecimal amount, Transaction theTransaction) {
        createAccountObject(accounts, fromAccountName, theTransaction);
        accounts.get(fromAccountName).deductAmount(amount);

        createAccountObject(accounts, toAccountName, theTransaction);
        accounts.get(toAccountName).addAmount(amount);
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
