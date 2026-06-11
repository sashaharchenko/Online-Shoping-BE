import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Transaction {
    private final String fromAccount;
    private final String toAccount;
    private final double amount;
    private final String productName;
    private final String paymentMethod;
    private final LocalDateTime timestamp;

    public Transaction(String fromAccount, String toAccount, double amount, String productName, String paymentMethod) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.productName = productName;
        this.paymentMethod = paymentMethod;
        this.timestamp = LocalDateTime.now();
    }

    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public double getAmount() { return amount; }
    public String getProductName() { return productName; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return timestamp.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s | %.2f руб. | Товар: %s | Оплата: %s",
                getFormattedTimestamp(), fromAccount, toAccount, amount, productName, paymentMethod);
    }
    public String GetProductName(){
        return productName;
    }
}