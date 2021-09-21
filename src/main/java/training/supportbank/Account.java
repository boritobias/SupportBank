package training.supportbank;

import java.util.ArrayList;

public class Account {
    private String name;
    private double amount;
    private ArrayList<Transaction> transactions;

    public Account(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void addAmount(double transaction) {
        this.amount = amount + transaction;
    }

    public void deductAmount(double transaction) {
        this.amount = amount - transaction;
    }

    public ArrayList getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String toString() {
        return "name: '" + name + '\'' +
                ", amount: '" + amount + '\'' +
                ", transactions: '" + transactions + '\'';
    }
}
