public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Оплата %.2f руб. наличными%n", amount);
    }

    @Override
    public String getMethodName() {
        return "Наличные";
    }
}
