package training.supportbank;

import java.math.BigDecimal;

public class Transaction {
    private String oweFrom;
    private String oweTo;
    private String date;
    private String narrative;
    private BigDecimal amount;

    public Transaction(String oweFrom, String oweTo, String date, String narrative, BigDecimal amount) {
        this.oweFrom = oweFrom;
        this.oweTo = oweTo;
        this.date = date;
        this.narrative = narrative;
        this.amount = amount;
    }

    public String getOweFrom() {
        return oweFrom;
    }

    public String getOweTo() {
        return oweTo;
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
        return "from: '" + oweFrom + '\'' +
                ", to: '" + oweTo + '\'' +
                ", date: '" + date + '\'' +
                ", narrative: '" + narrative + '\'' +
                ", amount: '" + amount + '\'';
    }
}
