
public class Electronic extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronic(String name, double price, String type, boolean payStatus, String description,
                      String brand, int warrantyMonths) {
        super(name, price, type, payStatus, description);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public Electronic(String name, double price, String type, boolean payStatus,
                      String brand, int warrantyMonths) {
        super(name, price, type, payStatus);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void showInfo() {
        System.out.printf("Электроника: %s | Бренд: %s | Цена: %.2f | Гарантия: %d мес. | Описание: %s%n",
                name, brand, price, warrantyMonths, description);
    }


    public String getBrand() { return brand; }
    public int getWarrantyMonths() { return warrantyMonths; }


    public void setBrand(String brand) { this.brand = brand; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

    @Override
    public String toString() {
        return String.format("Electronic{name='%s', price=%.2f, brand='%s', warranty=%d, description='%s'}",
                name, price, brand, warrantyMonths, description);
    }
}