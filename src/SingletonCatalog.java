import java.util.ArrayList;
import java.util.List;

public class SingletonCatalog {
    private static SingletonCatalog instance;
    private final List<Category> categories;

    private SingletonCatalog() {
        this.categories = new ArrayList<>();
    }

    public static SingletonCatalog getInstance() {
        if (instance == null) {
            instance = new SingletonCatalog();
        }
        return instance;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void clearCategories() {
        categories.clear();
    }

    public void showFullCatalog() {
        if (categories.isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }
        for (Category category : categories) {
            category.showFullHierarchy(0);
        }
    }

    public void showCategories() {
        if (categories.isEmpty()) {
            System.out.println("Категорий нет.");
            return;
        }
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}