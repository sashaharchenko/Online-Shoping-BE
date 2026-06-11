import java.util.Comparator;

public class CategoryComparator {

    public static Comparator<Category> sortByIdAscending() {
        return Comparator.comparingInt(Category::getId);
    }

    public static Comparator<Category> sortByIdDescending() {
        return sortByIdAscending().reversed();
    }

    public static Comparator<Category> sortByTitleAscending() {
        return Comparator.comparing(Category::getTitle, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Category> sortByTitleDescending() {
        return sortByTitleAscending().reversed();
    }

    public static Comparator<Category> sortByProductCountAscending() {
        return Comparator.comparingInt(Category::getTotalProductCount);
    }

    public static Comparator<Category> sortByProductCountDescending() {
        return sortByProductCountAscending().reversed();
    }

    public static Comparator<Category> sortBySubCategoryCountAscending() {
        return Comparator.comparingInt(Category::getSubCategoryCount);
    }

    public static Comparator<Category> sortBySubCategoryCountDescending() {
        return sortBySubCategoryCountAscending().reversed();
    }

    public static Comparator<Category> getComparator(int criterion, boolean ascending) {
        switch (criterion) {
            case 1:
                return ascending ? sortByIdAscending() : sortByIdDescending();

            case 2:
                return ascending ? sortByTitleAscending() : sortByTitleDescending();

            case 3:
                return ascending ? sortByProductCountAscending() : sortByProductCountDescending();

            case 4:
                return ascending ? sortBySubCategoryCountAscending() : sortBySubCategoryCountDescending();

            default:
                return null;
        }
    }
}