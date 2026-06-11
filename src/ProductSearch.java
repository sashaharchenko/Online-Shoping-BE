import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSearch {

    public static List<Product> searchByName(List<Product> products, String query) {
        String[] keywords = query.toLowerCase().split("\\s+");
        return products.stream()
                .filter(product -> {
                    String lowerName = product.getName().toLowerCase();
                    for (String keyword : keywords) {
                        if (lowerName.contains(keyword)) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public static List<Product> filterByMaxPrice(List<Product> products, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public static List<Product> filterByType(List<Product> products, String type) {
        return products.stream()
                .filter(p -> p.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public static List<Product> filterByPayStatus(List<Product> products, boolean paid) {
        return products.stream()
                .filter(p -> p.isPayStatus() == paid)
                .collect(Collectors.toList());
    }



    public static void printStatistics(List<Product> products) {
        System.out.println("\n=== СТАТИСТИКА ТОВАРОВ (Stream API) ===");
        long count = products.stream().count();
        System.out.println("Всего товаров: " + count);

        products.stream()
                .min((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .ifPresent(min -> System.out.println("Самый дешёвый: " + min.getName() + " (" + min.getPrice() + ")"));

        products.stream()
                .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .ifPresent(max -> System.out.println("Самый дорогой: " + max.getName() + " (" + max.getPrice() + ")"));

        products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .ifPresent(avg -> System.out.println("Средняя цена: " + String.format("%.2f", avg)));

        products.stream()
                .collect(Collectors.groupingBy(Product::getType, Collectors.counting()))
                .forEach((type, cnt) -> System.out.println("  " + type + ": " + cnt + " шт."));

        long paidCount = products.stream().filter(Product::isPayStatus).count();
        System.out.println("Оплаченные: " + paidCount);
        System.out.println("Неоплаченные: " + (count - paidCount));
    }
    public static List<Product> advancedFilter(List<Product> products, String filterString) throws ShopException {
        if (products == null) {
            throw new ShopException("Список товаров не может быть null");
        }
        if (filterString == null) {
            throw new ShopException("Строка фильтрации не может быть null");
        }

        String[] filters = filterString.toLowerCase().split("\\s+");
        List<Product> result = new ArrayList<>(products);

        for (String filter : filters) {

            if (filter.startsWith("price:")) {
                String[] parts = filter.split(":");
                if (parts.length == 2) {
                    try {
                        double maxPrice = Double.parseDouble(parts[1]);
                        if (maxPrice <= 0) {
                            throw new InvalidAmountException(maxPrice);
                        }
                        result = result.stream()
                                .filter(p -> p.getPrice() <= maxPrice)
                                .collect(Collectors.toList());
                    } catch (NumberFormatException e) {
                        throw new ShopException("Неверный формат цены: " + parts[1]);
                    }
                }
            }
        }
        return result;
    }
}