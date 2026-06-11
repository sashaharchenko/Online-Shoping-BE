public class InvalidAmountException extends ShopException {
    public InvalidAmountException(String message) {
        super("Неверная сумма: " + message);
    }

    public InvalidAmountException(double amount) {
        super("Неверная сумма перевода: " + amount + ". Сумма должна быть положительной.");
    }
}
