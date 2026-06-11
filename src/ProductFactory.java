public class ProductFactory {
    public Product createSimpleProduct(String name, double price, String type, boolean payStatus, String description) {
        SimpleProduct product = new SimpleProduct(name, price, type, payStatus);
        product.setDescription(description);
        return product;
    }




}