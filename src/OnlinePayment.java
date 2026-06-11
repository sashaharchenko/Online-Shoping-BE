public class OnlinePayment implements PaymentStrategy {
    private String walletId;

    public OnlinePayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Онлайн-оплата %.2f руб. через кошелёк %s%n", amount, walletId);
    }

    @Override
    public String getMethodName() {
        return "Онлайн-кошелёк";
    }
}