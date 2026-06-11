public class DataInitializer {
    private static final SingletonCatalog catalog = SingletonCatalog.getInstance();

    public static void init() throws ProductNotFoundException {
        ProductFactory factory = new ProductFactory();

        Category electronics = new Category("Электроника", "Техника и гаджеты");
        Category phones = new Category("Смартфоны", "Мобильные телефоны");
        Category laptops = new Category("Ноутбуки", "Компьютеры");
        Category books = new Category("Книги", "Художественная литература");
        Category fiction = new Category("Романы", "Классическая литература");
        Category garden = new Category("Сад и огород", "Товары для сада и дачи");

        electronics.addSubCategory(phones);
        electronics.addSubCategory(laptops);
        books.addSubCategory(fiction);

        phones.addProduct(factory.createSimpleProduct("iPhone 15", 50000, "Смартфон", false, "Красный цвет, 128GB"));
        phones.addProduct(factory.createSimpleProduct("Samsung Galaxy S24", 45000, "Смартфон", true, "Чёрный цвет, 256GB"));
        phones.addProduct(factory.createSimpleProduct("Xiaomi Mi 14", 35000, "Смартфон", false, "Синий цвет, 256GB"));

        laptops.addProduct(factory.createSimpleProduct("MacBook Pro", 65000, "Ноутбук", false, "Серый цвет, 16GB RAM"));
        laptops.addProduct(factory.createSimpleProduct("Lenovo IdeaPad", 30000, "Ноутбук", true, "Чёрный цвет, 8GB RAM"));
        laptops.addProduct(factory.createSimpleProduct("Acer Aspire", 25000, "Ноутбук", false, "Серебристый цвет, 8GB RAM"));

        fiction.addProduct(factory.createSimpleProduct("Идиот", 45.00, "Книга", false, "Достоевский, твёрдый переплёт"));
        fiction.addProduct(factory.createSimpleProduct("Преступление и наказание", 50.00, "Книга", true, "Достоевский, мягкий переплёт"));
        fiction.addProduct(factory.createSimpleProduct("Война и мир", 60.00, "Книга", false, "Толстой, 4 тома"));

        catalog.addCategory(electronics);
        catalog.addCategory(books);
        catalog.addCategory(garden);
    }
}