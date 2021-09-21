package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Transaction {
    private String oweFrom;
    private String oweTo;
    private String date;
    private String narrative;
    private double amount;

    public Transaction(String oweFrom, String oweTo, String date, String narrative, double amount) {
        this.oweFrom = oweFrom;
        this.oweTo = oweTo;
        this.date = date;
        this.narrative = narrative;
        this.amount = amount;
    }

    public String getOweFrom() {
        return oweFrom;
    }

    public void setOweFrom(String oweFrom) {
        this.oweFrom = oweFrom;
    }

    public String getOweTo() {
        return oweTo;
    }

    public void setOweTo(String oweTo) {
        this.oweFrom = oweTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String toString() {
        return "Transaction{" +
                "from='" + oweFrom + '\'' +
                ", to='" + oweTo + '\'' +
                ", date='" + date + '\'' +
                ", narrative='" + narrative + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String args[]) {

        Map<String, ArrayList> transactionsByPerson = new HashMap<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        String path = "src/Transactions2014.csv";
        String line = "";
        double bank = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (!values[1].equals("From")) {
                    transactions.add(new Transaction(values[1], values[2], values[0], values[3], Double.parseDouble(values[4])));
                    transactionsByPerson.put(values[1], transactions);
                }
            }

            // Print out Sarah T's transactions one by one
            transactionsByPerson.get("Sarah T").forEach(e -> System.out.println(e));
            // Print out Sarah T's transactions on one line
            // System.out.println(transactionsByPerson.get("Sarah T"));
            // Print out all transactions by everyone
            // System.out.println(transactionsbyPerson);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
