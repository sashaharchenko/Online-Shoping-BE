import java.util.ArrayList;
abstract class Product{
    protected String name;
    protected double price;
    protected  String type;

    public Product(String name, double price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public abstract void showInfo();


    public void add(Product products) {

    }
}