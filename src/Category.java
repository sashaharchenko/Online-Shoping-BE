import java.util.ArrayList;
import java.util.List;

class Category {
    private String categoryName;
    private ArrayList<Product> products;
    protected int id;
    protected String title;
    protected String descripton;

    public Category(String categoryName){
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
    }
    public void addProduct(Product products){
        products.add(products);
        System.out.println("Товар: \"" + products.name +  "\"Добавлен в категорию: " + categoryName + "\"");
    }

    public void showAllProducts(){
        System.out.println(categoryName);
        if (products.isEmpty()){
            System.out.println("Нет товаров");
        } else {
            for(Product p : products){
                p.showInfo();
            }
        }
        System.out.println("Всего товаров: " + products.size());


    }





    private static int nextId = 1;
    private static List<Category> categories = new ArrayList<>();


    public Category() { }

    public int getSubCategoryCount(){
        return categories.size();
    }
    public void addSubCategory(Category category){
        this.categories.add(category);
    }

    public Category( int id, String title, String descripton){
    }

    public Category(String title, String descripiton) {
        this.title = title;
        this.descripton = descripiton;
    }


    public void showInfo() {

    }

    public String getType() {
        return null;
    }

    public int getId() {
        return  id;
    }
    public String getTitle() {
        return  title;
    }
    public String getDestription() {
        return descripton;
    }

    public void setTitle (String title){
        this.title = title;
    }

    public void setDescripton ( String descripton){
        this.descripton = descripton;
    }
    public static void  addCategory(Category category) {
        categories.add(category);
        System.out.println(category.getTitle());
    }
    public static void showCategories() {
        if (categories.isEmpty()) {
            System.out.println(" ");
            return;
        }


    }
}