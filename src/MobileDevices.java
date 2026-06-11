
public class MobileDevices extends Electronic {
    private String os;
    private double screenSize;
    private int batteryCapacity;

    public MobileDevices(String name, double price, String type, boolean payStatus, String description,
                         String brand, int warrantyMonths, String os, double screenSize, int batteryCapacity) {
        super(name, price, type, payStatus, description, brand, warrantyMonths);
        this.os = os;
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void showInfo() {
        System.out.printf("Мобильное устройство: %s | Бренд: %s | ОС: %s | Экран: %.1f\" | Батарея: %d мАч | Цена: %.2f%n",
                name, getBrand(), os, screenSize, batteryCapacity, price);
    }


    public String getOs() { return os; }
    public double getScreenSize() { return screenSize; }
    public int getBatteryCapacity() { return batteryCapacity; }


    public void setOs(String os) { this.os = os; }
    public void setScreenSize(double screenSize) { this.screenSize = screenSize; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    @Override
    public String toString() {
        return String.format("MobileDevices{name='%s', price=%.2f, brand='%s', os='%s', screen=%.1f, battery=%d}",
                name, price, getBrand(), os, screenSize, batteryCapacity);
    }
}