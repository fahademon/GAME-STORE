package sample;

public class CreditCardPayment extends Payment{
    String cvv, expirationDate, creditCardNumber;

    private CreditCardPayment(){

    }
    public static CreditCardPayment getInstance()
    {
        if(instance == null)
            return new CreditCardPayment();
        return (CreditCardPayment) instance;
    }



    public void setDetails(String cardNumber, String expiration, String cvv, Double amount) {
        this.creditCardNumber = cardNumber;
        this.expirationDate = expiration;
        this.cvv = cvv;
        this.amount = amount;
    }

    @Override
    public boolean process() {
        return true;
    }
}
