public class InsufficientFundsException extends ShopException {
    private final double required;
    private final double available;

    public InsufficientFundsException(double required, double available) {
        super(String.format("Недостаточно средств! Требуется: %.2f руб. Доступно: %.2f руб.", required, available));
        this.required = required;
        this.available = available;
    }

    public double getRequired() {
        return required;
    }

    public double getAvailable() {
        return available;
    }
}
