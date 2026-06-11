public class ProductNotFoundException extends ShopException {
    public ProductNotFoundException(String productName) {
        super("Товар не найден: " + productName);
    }

    public ProductNotFoundException(int index) {
        super("Товар с индексом " + index + " не найден");
    }
}
