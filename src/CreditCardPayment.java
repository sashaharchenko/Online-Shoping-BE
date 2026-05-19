public class CreditCardPayment implements PaymentStrategy{
    private String cardNumber;
    public CreditCardPayment(String cardNumber){
        this.cardNumber=cardNumber;
    }
    @Override
    public void pay(double amount){
        System.out.printf("Оплата %.2f руб банковской картой %%n",amount,maskCardNumber() );
    }
    @Override
    public String getMethodName(){return "Банковска карта";}

    private String maskCardNumber(){
        return "****" + cardNumber.substring(cardNumber.length() - 4);
    }
}

