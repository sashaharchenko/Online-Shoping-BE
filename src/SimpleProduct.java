public class SimpleProduct extends Product implements Finansible {
    private double balance = 50000;
    private static int totalProductsCreated = 0;

    public SimpleProduct(String name, double price, String type, boolean payStatus) {
        super(name, price, type, payStatus);
        totalProductsCreated++;
    }

    public SimpleProduct(String name, double price, String type, boolean payStatus, double balance) {
        super(name, price, type, payStatus);
        this.balance = balance;
        totalProductsCreated++;
    }

    public static int getTotalProductsCreated() {
        return totalProductsCreated;
    }

    @Override
    public void showInfo() {
        System.out.printf("Товар: %s | Цена: %.2f | Тип: %s | Оплачен: %s | Описание: %s%n",
                name, price, type, payStatus, description);
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinalStatus() {
        return payStatus ? OrderStatus.PAID.getDescription() : OrderStatus.PENDING.getDescription();
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        }
        this.balance = balance;
    }
}