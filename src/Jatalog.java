//todo 3 поля 1 поле айди(int) 2 поле title(string) 3 поле price(double) В этом классе применяем инкапсуляцию.
//todo Создаём 2 обьекта в main и запускаем. создаём 2 экземлпяра
public class Jatalog {
    private int id;
    private String title;
    private double price;

    public Jatalog(){}

    public Jatalog(String title,int id, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public int getId(){
        return this.id;
    }
    public void SetId(int id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void SetTitle(String title){
        this.title = title;
    }
    public double getPrice(){
        return this.price;
    }
    public void SetPrice(double price){
        this.price = price;
    }

}
