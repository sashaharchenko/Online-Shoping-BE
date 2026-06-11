public class PaymentException extends ShopException {
    public PaymentException(String message) {
        super("Ошибка оплаты: " + message);
    }
}
