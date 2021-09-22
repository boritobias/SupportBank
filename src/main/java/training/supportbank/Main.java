package training.supportbank;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String args[]) {

        Map<String, Account> accounts = new HashMap<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

//        String csv = "Transactions2014.csv";
//        String json = "Transactions2013.json";

        FileReader fileReader = new FileReader(accounts, transactions);

        boolean noMoreFiles = false;
        while (accounts.isEmpty() || !noMoreFiles) {
            Scanner newScan = new Scanner(System.in);
            System.out.println("Enter the filename you want to read. If you have every file, type stop");
            String command = newScan.nextLine();
            if (command.toLowerCase().equals("stop")) {
                noMoreFiles = true;
            } else {
                fileReader.readFile(command);
            }
        }

        printListByUserInput(accounts);
    }

    private static void printListByUserInput(Map<String, Account> accounts) {
        ArrayList<String> people = new ArrayList<>(accounts.keySet());

        boolean commandToQuit = false;
        while (commandToQuit == false) {
            Scanner newScan = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter an account name or \"all\" to list all accounts. If you wish to quit, type stop");
            String command = newScan.nextLine();
            if (command.toLowerCase().equals("all")) {
                accounts.forEach((k, v) -> System.out.println(k + "'s account is at " + v.getAmount() + " GBP"));
            } else if (people.contains(command)) {
                accounts.get(command).getTransactions().forEach(System.out::println);
            } else if (command.toLowerCase().equals("stop")) {
                commandToQuit = true;
            } else {
                System.out.println("Sorry, this is not a valid option. Please try again.");
            }
        }
    }

}
