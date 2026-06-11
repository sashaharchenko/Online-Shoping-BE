public class CategoryNotFoundException extends ShopException {
    public CategoryNotFoundException(String categoryName) {
        super("Категория не найдена: " + categoryName);
    }

    public CategoryNotFoundException(int id) {
        super("Категория с id=" + id + " не найдена");
    }
}
