public class Main {
    public static void main(String[] args) {

        Category category = new Category("Мои товары");

        Product product1 = new Product("iPhone 15", 50000, "Электроника") {
            @Override
            public void showInfo() {
                System.out.println("телефон " + type + ": " + name + " | Цена: $" + price);
            }
        };

        Product product2 = new Product("MacBook Pro", 65000, "Электроника") {
            @Override
            public void showInfo() {
                System.out.println("ноутбук " + type + ": " + name + " | Цена: $" + price);
            }
        };

        Product product3 = new Product("Футболка хлопковая", 1000, "Одежда") {
            @Override
            public void showInfo() {
                System.out.println("Одежда " + type + ": " + name + " | Цена: $" + price);
            }
        };

        Product product4 = new Product("Куртка демисезонная", 5000, "Одежда") {
            @Override
            public void showInfo() {
                System.out.println("Одежда " + type + ": " + name + " | Цена: $" + price);
            }
        };

        Product product5 = new Product("Идиот", 45.00, "Книга") {
            @Override
            public void showInfo() {
                System.out.println("Книга " + type + ": " + name + " | Цена: $" + price);
            }
        };

        category.addProduct(product1);
        category.addProduct(product2);
        category.addProduct(product3);
        category.addProduct(product4);
        category.addProduct(product5);


        category.showAllProducts();
    }
}