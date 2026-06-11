import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Category implements Comparable<Category> {
    private static int nextId = 1;
    private static final List<Category> categories = new ArrayList<>();
    private static int totalCategoriesCount = 0;
    private static int totalSubCategoriesCount = 0;

    private int id;
    private String title;
    private String description;

    private final List<Product> products = new ArrayList<>();
    private final List<Category> subCategories = new ArrayList<>();

    public Category(String title) {
        this(title, "");
    }

    public Category(String title, String description) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        totalCategoriesCount++;
    }

    public Category(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        if (id >= nextId) {
            nextId = id + 1;
        }
        totalCategoriesCount++;
    }

    @Override
    public int compareTo(Category other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    public void addProduct(Product product) {
        this.products.add(product);
        System.out.printf("Товар \"%s\" добавлен в категорию \"%s\"%n", product.getName(), title);
    }

    public void addSubCategory(Category category) {
        this.subCategories.add(category);
        totalSubCategoriesCount++;
    }

    public void showCategoryInfo() {
        System.out.printf("Категория: %s (id: %d) | Описание: %s | Товаров: %d | Подкатегорий: %d%n",
                title, id, description, products.size(), subCategories.size());
    }


    public static void addNewCategory(Category category) {
        categories.add(category);
    }


    public static void showAllCategories() {
        if (categories.isEmpty()) {
            System.out.println("Категорий нет.");
            return;
        }
        System.out.println("\n=== ВСЕ КАТЕГОРИИ ===");
        for (Category cat : categories) {
            cat.showCategoryInfo();
        }
    }


    public static void showCategoryStatistics() {
        System.out.println("\n=== СТАТИСТИКА КАТЕГОРИЙ ===");
        System.out.printf("Всего категорий: %d%n", totalCategoriesCount);
        System.out.printf("Всего подкатегорий: %d%n", totalSubCategoriesCount);
    }

    public void showFullHierarchy(int level) {
        String indent = makeIndent(level);
        System.out.printf("%s[Категория] %s | id: %d | Описание: %s%n", indent, title, id, description);
        for (Product product : products) {
            System.out.printf("%s    [Товар] %s | Цена: %.2f | Тип: %s | Оплачен: %s%n",
                    indent, product.getName(), product.getPrice(), product.getType(), product.isPayStatus());
        }
        for (Category subCategory : subCategories) {
            subCategory.showFullHierarchy(level + 1);
        }
    }

    public boolean isSameCategoryType(Category other) {
        if (other == null) return false;
        return this.getClass() == other.getClass();
    }

    private String makeIndent(int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append("    ");
        }
        return builder.toString();
    }

    public int getTotalProductCount() {
        int count = products.size();
        for (Category subCategory : subCategories) {
            count += subCategory.getTotalProductCount();
        }
        return count;
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void showCategories() {
        if (categories.isEmpty()) {
            System.out.println("Категорий нет.");
            return;
        }
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    public static void showFullCatalog() {
        if (categories.isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }
        for (Category category : categories) {
            category.showFullHierarchy(0);
        }
    }

    public static void sortAllCategories(Comparator<Category> comparator) {
        if (comparator == null) {
            Collections.sort(categories);
        } else {
            categories.sort(comparator);
        }
        for (Category category : categories) {
            category.sortSubCategories(comparator);
        }
    }

    private void sortSubCategories(Comparator<Category> comparator) {
        if (comparator == null) {
            Collections.sort(subCategories);
        } else {
            subCategories.sort(comparator);
        }
        for (Category subCategory : subCategories) {
            subCategory.sortSubCategories(comparator);
        }
    }


    private static Category findCategoryByIdRecursive(Category category, int id) {
        if (category.getId() == id) return category;
        for (Category subCategory : category.getSubCategories()) {
            Category found = findCategoryByIdRecursive(subCategory, id);
            if (found != null) return found;
        }
        return null;
    }


    public static List<Category> getCategories() { return categories; }
    public static int getTotalCategoriesCount() { return totalCategoriesCount; }      // ДОБАВЛЕНО
    public static int getTotalSubCategoriesCount() { return totalSubCategoriesCount; } // ДОБАВЛЕНО
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<Product> getProductsList() { return products; }
    public List<Category> getSubCategories() { return subCategories; }
    public int getSubCategoryCount() { return subCategories.size(); }


    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("Category{id=%d, title='%s', description='%s', products=%d, subCategories=%d, totalProducts=%d}",
                id, title, description, products.size(), subCategories.size(), getTotalProductCount());
    }
    public static Category findCategoryByIdStatic(int id) throws CategoryNotFoundException{
        for (Category category: categories){
            Category found=findCategoryByIdRecursive(category,id);
            if(found != null){
                return found;
            }
        }
        throw new CategoryNotFoundException(id);
    }
}