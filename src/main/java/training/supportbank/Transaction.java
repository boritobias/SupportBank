package training.supportbank;

public class Transaction {
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

    public String getOweTo() {
        return oweTo;
    }

    public String getDate() {
        return date;
    }

    public String getNarrative() {
        return narrative;
    }

    public double getAmount() {
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
