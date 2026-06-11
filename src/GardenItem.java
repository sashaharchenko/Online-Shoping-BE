
public class GardenItem extends Product {
    private String material;
    private boolean isSeasonal;

    public GardenItem(String name, double price, String type, boolean payStatus, String description,
                      String material, boolean isSeasonal) {
        super(name, price, type, payStatus, description);
        this.material = material;
        this.isSeasonal = isSeasonal;
    }

    public GardenItem(String name, double price, String type, boolean payStatus,
                      String material, boolean isSeasonal) {
        super(name, price, type, payStatus);
        this.material = material;
        this.isSeasonal = isSeasonal;
    }

    @Override
    public void showInfo() {
        System.out.printf("Сад/Огород: %s | Материал: %s | Цена: %.2f | Сезонный: %s | Описание: %s%n",
                name, material, price, isSeasonal ? "Да" : "Нет", description);
    }

    public String getMaterial() {
        return material;
    }

    public boolean isSeasonal() {
        return isSeasonal;
    }


    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSeasonal(boolean seasonal) {
        isSeasonal = seasonal;
    }

    @Override
    public String toString() {
        return String.format("GardenItem{name='%s', price=%.2f, material='%s', seasonal=%s, description='%s'}",
                name, price, material, isSeasonal, description);
    }
}