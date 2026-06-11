import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShopApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SingletonCatalog catalog = SingletonCatalog.getInstance();
    private static final TransactionHistory history = TransactionHistory.getInstance();

    public static void start() {
        try {
            DataInitializer.init();
        } catch (Exception e) {
            System.err.println("Ошибка инициализации: " + e.getMessage());
        }

        while (true) {
            try {
                printMenu();
                int command = readInt("Выберите команду: ");

                switch (command) {
                    case 1:
                        catalog.showCategories();
                        break;
                    case 2:
                        catalog.showFullCatalog();
                        break;
                    case 3:
                        sortCategoriesMenu();
                        break;
                    case 4:
                        compareProductsMenu();
                        break;
                    case 5:
                        filterProductsMenu();
                        break;
                    case 6:
                        checkOrderStatuses();
                        break;
                    case 7:
                        checkBalanceWithLambda();
                        break;
                    case 8:
                        searchProductByName();
                        break;
                    case 9:
                        advancedFilterMenu();
                        break;
                    case 10:
                        showProductStatistics();
                        break;
                    case 11:
                        makeTransaction();
                        break;
                    case 12:
                        history.showHistory();
                        break;
                    case 13:
                        Category.showCategoryStatistics();
                        break;
                    case 14:
                        StoreChecklist.printChecklist();
                        break;
                    case 0:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверная команда.");
                }
            } catch (ShopException e) {
                System.err.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Непредвиденная ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("    МЕНЮ     ");
        System.out.println("1. Проверить категории");
        System.out.println("2. Проверить товары и полную цепочку категорий");
        System.out.println("3. Сортировать категории");
        System.out.println("4. Сравнить товары");
        System.out.println("5. Фильтрация товаров ");
        System.out.println("6. Проверить статусы заказов ");
        System.out.println("7. Проверить баланс ");
        System.out.println("8. Поиск товара по названию ");
        System.out.println("9. Расширенная фильтрация товаров");
        System.out.println("10. Статистика товаров ");
        System.out.println("11. Транзакция ");
        System.out.println("12. История Транзакций");
        System.out.println("13. Статистика Категорий");
        System.out.println("14. Чек-Лист Магазина");
        System.out.println("0. Выход");
    }

    private static void sortCategoriesMenu() throws ShopException {
        System.out.println("\n=== Сортировка категорий ===");
        System.out.println("1. По id");
        System.out.println("2. По названию");
        System.out.println("3. По количеству товаров");
        System.out.println("4. По количеству подкатегорий");
        System.out.println("5. По умолчанию через Comparable");

        int criterion = readInt("Выберите критерий: ");
        if (criterion < 1 || criterion > 5) {
            throw new ShopException("Неверный критерий: " + criterion);
        }

        Comparator<Category> comparator = null;
        if (criterion != 5) {
            System.out.println("1. По возрастанию");
            System.out.println("2. По убыванию");
            int direction = readInt("Выберите направление: ");
            boolean ascending = direction == 1;
            comparator = CategoryComparator.getComparator(criterion, ascending);
            if (comparator == null) {
                throw new ShopException("Не удалось получить компаратор");
            }
        }


        List<Category> categories = catalog.getCategories();
        if (comparator == null) categories.sort(null);
        else categories.sort(comparator);

        System.out.println("Категории отсортированы:");
        catalog.showFullCatalog();
    }

    private static void compareProductsMenu() throws ShopException {
        List<ProductMenuItem> products = getAllProductMenuItems();
        if (products.size() < 2) {
            throw new ShopException("Недостаточно товаров для сравнения (нужно минимум 2)");
        }

        System.out.println("\n   Список товаров   ");
        for (int i = 0; i < products.size(); i++) {
            ProductMenuItem item = products.get(i);
            System.out.printf("%d. %s | Цена: %.2f | Тип: %s%n",
                    i + 1, item.product.getName(), item.product.getPrice(), item.product.getType());
        }

        int firstIndex = readInt("Выберите первый товар: ") - 1;
        int secondIndex = readInt("Выберите второй товар: ") - 1;

        if (firstIndex < 0 || firstIndex >= products.size()) throw new ProductNotFoundException(firstIndex + 1);
        if (secondIndex < 0 || secondIndex >= products.size()) throw new ProductNotFoundException(secondIndex + 1);

        Product first = products.get(firstIndex).product;
        Product second = products.get(secondIndex).product;

        System.out.println("\nКритерии сравнения:");
        System.out.println("1. Название | 2. Цена | 3. Тип | 4. Оплата | 5. Описание");
        int criterion = readInt("Выберите критерий: ");

        if (!ProductComparator.canCompare(first, second)) {
            throw new ShopException("Нельзя сравнивать разные типы товаров");
        }

        Comparator<Product> comp = ProductComparator.getComparator(criterion);
        if (comp == null) throw new ShopException("Неверный критерий");

        int result = comp.compare(first, second);
        System.out.println(result == 0 ? "Товары равны" : (result < 0 ? "Первый меньше" : "Второй меньше"));
    }

    private static void filterProductsMenu() {
        List<Product> all = getAllProducts();
        if (all.isEmpty()) {
            System.out.println("Нет товаров");
            return;
        }

        System.out.println("\n=== Фильтрация ===");
        System.out.println("1. По цене | 2. По типу | 3. По оплате | 4. По названию | 5. Комбинированный | 6. Сортировка по цене");
        int choice = readInt("Выберите: ");

        List<Product> filtered = new ArrayList<>();
        try {
            switch (choice) {
                case 1:
                    double max = readDouble("Макс. цена: ");
                    if (max <= 0) throw new InvalidAmountException(max);
                    filtered = all.stream().filter(p -> p.getPrice() <= max).collect(Collectors.toList());
                    break;
                case 2:
                    String type = readString("Тип: ");
                    filtered = all.stream().filter(p -> p.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
                    break;
                case 3:
                    boolean paid = readBoolean("Оплаченные? (да/нет): ");
                    filtered = all.stream().filter(p -> p.isPayStatus() == paid).collect(Collectors.toList());
                    break;
                case 4:
                    String sub = readString("Подстрока: ");
                    filtered = all.stream().filter(p -> p.getName().toLowerCase().contains(sub.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    double limit = readDouble("Макс. цена: ");
                    String t = readString("Тип: ");
                    boolean onlyPaid = readBoolean("Только оплаченные? ");
                    filtered = all.stream()
                            .filter(p -> p.getPrice() <= limit && p.getType().equalsIgnoreCase(t) && p.isPayStatus() == onlyPaid)
                            .collect(Collectors.toList());
                    break;
                case 6:
                    filtered = all.stream().sorted(Comparator.comparingDouble(Product::getPrice)).collect(Collectors.toList());
                    break;
                default:
                    throw new ShopException("Неверный выбор");
            }
            if (filtered.isEmpty()) System.out.println("Товары не найдены");
            else filtered.forEach(p -> System.out.printf("%s | %.2f | %s | Оплачен: %s%n",
                    p.getName(), p.getPrice(), p.getType(), p.isPayStatus()));
        } catch (ShopException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void checkOrderStatuses() {
        OrderStatus.showAll();
        System.out.println("\n    Статусы товаров    ");
        for (Product p : getAllProducts()) {
            String status = p.isPayStatus() ? OrderStatus.PAID.getDescription() : OrderStatus.PENDING.getDescription();
            System.out.printf("%s -> %s%n", p.getName(), status);
        }
    }

    private static void checkBalanceWithLambda() {
        System.out.println("\n    Проверка баланса    ");
        java.util.function.Predicate<Double> hasMoney = b -> b > 0;
        double balance = 10000;
        System.out.println(hasMoney.test(balance) ? "✓ Платёжеспособен" : "✗ Не платёжеспособен");
    }

    private static void searchProductByName() {
        List<Product> all = getAllProducts();
        if (all.isEmpty()) {
            System.out.println("Нет товаров");
            return;
        }

        String query = readString("Введите ключевые слова: ");
        String[] keywords = query.toLowerCase().split("\\s+");

        List<Product> found = all.stream()
                .filter(p -> {
                    String name = p.getName().toLowerCase();
                    for (String kw : keywords) if (name.contains(kw)) return true;
                    return false;
                })
                .collect(Collectors.toList());

        if (found.isEmpty()) System.out.println("Не найдено");
        else found.forEach(p -> System.out.printf("  - %s | %.2f%n", p.getName(), p.getPrice()));
    }

    private static void advancedFilterMenu() {
        List<Product> all = getAllProducts();
        if (all.isEmpty()) {
            System.out.println("Нет товаров");
            return;
        }

        System.out.println("\n    Расширенная фильтрация    ");
        System.out.println("Форматы: price:число, type:строка, paid:true/false, name:строка");
        String filter = readString("Введите фильтры: ");

        try {
            List<Product> result = ProductSearch.advancedFilter(all, filter);
            if (result.isEmpty()) System.out.println("Не найдено");
            else result.forEach(p -> System.out.printf("  - %s | %.2f%n", p.getName(), p.getPrice()));
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showProductStatistics() {
        List<Product> all = getAllProducts();
        if (all.isEmpty()) {
            System.out.println("Нет товаров");
            return;
        }
        ProductSearch.printStatistics(all);
        System.out.printf("Всего создано товаров: %d%n", SimpleProduct.getTotalProductsCreated());
    }

    private static void makeTransaction() throws ShopException {
        List<Product> all = getAllProducts();
        if (all.isEmpty()) throw new ShopException("Нет товаров");

        System.out.println("\n    Оформление покупки    ");
        for (int i = 0; i < all.size(); i++) {
            Product p = all.get(i);
            System.out.printf("%d. %s | %.2f%n", i + 1, p.getName(), p.getPrice());
        }

        int idx = readInt("Выберите товар: ") - 1;
        if (idx < 0 || idx >= all.size()) throw new ProductNotFoundException(idx + 1);

        Product selected = all.get(idx);
        System.out.printf("Выбран: %s, сумма: %.2f%n", selected.getName(), selected.getPrice());

        System.out.println("1. Карта | 2. Наличные | 3. Онлайн");
        int payChoice = readInt("Выберите способ: ");
        PaymentStrategy strategy;

        switch (payChoice) {
            case 1:
                String card = readString("Номер карты: ");
                if (card.length() < 4) throw new PaymentException("Неверный номер");
                strategy = new CreditCardPayment(card);
                break;
            case 2:
                strategy = new CashPayment();
                break;
            case 3:
                String wallet = readString("ID кошелька: ");
                if (wallet.trim().isEmpty()) throw new PaymentException("Пустой ID");
                strategy = new OnlinePayment(wallet);
                break;
            default:
                throw new ShopException("Неверный способ");
        }

        if (selected instanceof SimpleProduct) {
            SimpleProduct sp = (SimpleProduct) selected;
            if (!sp.hasEnoughMoney(selected.getPrice())) {
                throw new InsufficientFundsException(selected.getPrice(), sp.checkBalance());
            }
            sp.pay(selected.getPrice());
        } else {
            selected.pay(selected.getPrice());
        }

        strategy.pay(selected.getPrice());
        Transaction transaction = new Transaction("Покупатель", "Магазин", selected.getPrice(),
                selected.getName(), strategy.getMethodName());
        history.addTransaction(transaction);
        System.out.printf("Транзакция успешна! Время: %s%n", transaction.getFormattedTimestamp());
    }

    private static List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        for (Category cat : catalog.getCategories()) collectProducts(cat, result);
        return result;
    }

    private static void collectProducts(Category cat, List<Product> result) {
        result.addAll(cat.getProductsList());
        for (Category sub : cat.getSubCategories()) collectProducts(sub, result);
    }

    private static List<ProductMenuItem> getAllProductMenuItems() {
        List<ProductMenuItem> result = new ArrayList<>();
        for (Category cat : catalog.getCategories()) collectMenuItems(cat, cat.getTitle(), result);
        return result;
    }

    private static void collectMenuItems(Category cat, String path, List<ProductMenuItem> result) {
        for (Product p : cat.getProductsList()) result.add(new ProductMenuItem(p, path));
        for (Category sub : cat.getSubCategories()) collectMenuItems(sub, path + " -> " + sub.getTitle(), result);
    }

    private static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    private static String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static boolean readBoolean(String msg) {
        System.out.print(msg);
        String in = scanner.nextLine();
        return in.equalsIgnoreCase("да") || in.equalsIgnoreCase("yes") || in.equals("1");
    }

    private static class ProductMenuItem {
        Product product;
        String path;

        ProductMenuItem(Product p, String path) {
            this.product = p;
            this.path = path;
        }
    }
}