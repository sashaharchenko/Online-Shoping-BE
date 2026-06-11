import java.util.List;

public class StoreChecklist {


    public static String generateChecklist() {
        List<Category> categories = SingletonCatalog.getInstance().getCategories();
        List<Transaction> transactions = TransactionHistory.getInstance().getTransactions();

        StringBuilder table = new StringBuilder();


        table.append("\n");
        table.append("=".repeat(120)).append("\n");
        table.append(String.format("%-25s | %-30s | %-15s | %-20s | %-20s%n",
                "КАТЕГОРИЯ", "ТОВАР", "СТАТУС ОПЛАТЫ", "СПОСОБ ОПЛАТЫ", "ВРЕМЯ"));
        table.append("=".repeat(120)).append("\n");

        int paidCount = 0;


        for (Category category : categories) {
            paidCount += collectPaidProductsFromCategory(category, category.getTitle(), table, transactions);
        }

        if (paidCount == 0) {
            table.append(String.format("%-120s%n", "НЕТ ОПЛАЧЕННЫХ ТОВАРОВ"));
        }

        table.append("=".repeat(120)).append("\n");
        table.append(String.format("Всего оплаченных товаров: %d%n", paidCount));
        table.append("=".repeat(120)).append("\n");

        return table.toString();
    }

    private static int collectPaidProductsFromCategory(Category category, String categoryPath, StringBuilder table, List<Transaction> transactions) {
        int count = 0;

        for (Product product : category.getProductsList()) {

            if (!product.isPayStatus()) {
                continue;
            }

            String status = "ОПЛАЧЕН";
            String paymentMethod = "—";
            String time = "—";

            for (Transaction t : transactions) {
                if (t.getProductName().equals(product.getName()) && Double.compare(t.getAmount(), product.getPrice()) == 0) {
                    paymentMethod = t.getPaymentMethod();
                    time = t.getFormattedTimestamp();
                    break;
                }
            }

            table.append(String.format("%-25s | %-30s | %-15s | %-20s | %-20s%n",
                    truncate(categoryPath, 25),
                    truncate(product.getName(), 30),
                    status,
                    truncate(paymentMethod, 20),
                    truncate(time, 20)
            ));
            count++;
        }

        for (Category subCategory : category.getSubCategories()) {
            count += collectPaidProductsFromCategory(subCategory, categoryPath + " -> " + subCategory.getTitle(), table, transactions);
        }

        return count;
    }

    private static String truncate(String str, int maxLen) {
        if (str == null || str.length() <= maxLen) {
            return str == null ? "—" : str;
        }
        return str.substring(0, maxLen - 3) + "...";
    }

    public static void printChecklist() {
        System.out.println(generateChecklist());
    }
}