package training.supportbank;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
    private String name;
    private BigDecimal amount;
    private ArrayList<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        this.amount = new BigDecimal(0);
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void addAmount(BigDecimal transaction) {
        this.amount = amount.add(transaction);
    }

    public void deductAmount(BigDecimal transaction) {
        this.amount = amount.subtract(transaction);
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
