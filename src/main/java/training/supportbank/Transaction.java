package training.supportbank;

import java.math.BigDecimal;

public class Transaction {
    private String fromAccount;
    private String toAccount;
    private String date;
    private String narrative;
    private BigDecimal amount;

    public Transaction(String date, String fromAccount, String toAccount, String narrative, BigDecimal amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.date = date;
        this.narrative = narrative;
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getDate() {
        return date;
    }

    public String getNarrative() {
        return narrative;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String toString() {
        return "date: '" + date + '\'' +
                ", from: '" + fromAccount + '\'' +
                ", to: '" + toAccount + '\'' +
                ", narrative: '" + narrative + '\'' +
                ", amount: '" + amount + '\'';
    }
}
