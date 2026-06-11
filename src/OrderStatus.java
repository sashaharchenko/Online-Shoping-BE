public enum OrderStatus {
    PENDING("Ожидает оплаты"),
    PAID("Оплачен"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void showAll() {
        System.out.println("=== Статусы заказа ===");
        for (OrderStatus status : values()) {
            System.out.println(status.ordinal() + 1 + ". " + status.getDescription());
        }
    }
}