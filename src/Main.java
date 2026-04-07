public class Main{
public static void main(String[] args) {
    Jatalog jatalog1 = new Jatalog("Ноутбук",1,45000);
    Jatalog jatalog2 = new Jatalog("Телефон",2,30000);
    System.out.println(jatalog1);
    System.out.println(jatalog2);

}
}
//todo Этап 1 2 шаг: в классе category должен быть поля сначал еще добавляем description, category класс должен быть
// todo абстракным будет id title price description, id должно определенным методом генеририватсья который увеличивает id
// todo на +1. и создаём новые наследники Electronic and GardenItem. Создаем еще 1 класс MobileDevice наследник Electronic
// todo Создаём несколько объектов из новых классов в main. В категории должен быть список ArrayList(тип данных category) и он сохраняет в себе
// todo и он сохраняет в себе Список категорий.Потом надо добавить в category 2 метода: addCategory and ShowCategory.
//todo


    Category electronic = new Category("Electronic");
    Category phones = new Category("phones");
    electronic.addSubCategory(phones);


