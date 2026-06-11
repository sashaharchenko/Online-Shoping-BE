import java.util.Comparator;

public class ProductComparator {

    public static Comparator<Product> sortByName() {
        return Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Product> sortByPrice() {
        return Comparator.comparingDouble(Product::getPrice);
    }

    public static Comparator<Product> sortByType() {
        return Comparator.comparing(Product::getType, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Product> sortByPayStatus() {
        return Comparator.comparing(Product::isPayStatus);
    }


    public static Comparator<Product> sortByDescription() {
        return Comparator.comparing(Product::getDescription, String.CASE_INSENSITIVE_ORDER);
    }


    public static boolean canCompare(Product p1, Product p2) {
        if (p1 == null || p2 == null) return false;

        return p1.getClass() == p2.getClass();
    }

    public static Comparator<Product> getComparator(int criterion) {
        switch (criterion) {
            case 1: return sortByName();
            case 2: return sortByPrice();
            case 3: return sortByType();
            case 4: return sortByPayStatus();
            case 5: return sortByDescription();  // ДОБАВЛЕНО
            default: return null;
        }
    }
}