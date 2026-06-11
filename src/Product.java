import java.util.Objects;

public abstract class Product implements Pable {
    protected String name;
    protected double price;
    protected String type;
    protected boolean payStatus;
    protected String description;

    public Product(String name, double price, String type, boolean payStatus, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.payStatus = payStatus;
        this.description = description;
    }

    public Product(String name, double price, String type, boolean payStatus) {
        this(name, price, type, payStatus, "");
    }

    public abstract void showInfo();

    @Override
    public double getFinalPrice() {
        return price;
    }

    @Override
    public void pay(double amount) {
        if (amount >= price) {
            payStatus = true;
        }
    }

    @Override
    public boolean isPay() {
        return payStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0
                && payStatus == product.payStatus
                && Objects.equals(name, product.name)
                && Objects.equals(type, product.type)
                && Objects.equals(description, product.description);  // ДОБАВЛЕНО
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type, payStatus, description);
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, type='%s', payStatus=%s, description='%s'}",
                name, price, type, payStatus, description);
    }


    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getType() { return type; }
    public boolean isPayStatus() { return payStatus; }
    public String getDescription() { return description; }


    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setType(String type) { this.type = type; }
    public void setPayStatus(boolean payStatus) { this.payStatus = payStatus; }
    public void setDescription(String description) { this.description = description; }
}