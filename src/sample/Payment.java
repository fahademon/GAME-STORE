package sample;

public abstract class Payment {
    protected static Payment instance = null;
    protected Double amount;


    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount= amount;
    }

    public abstract boolean process();

}
