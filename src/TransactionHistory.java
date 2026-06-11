import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionHistory {
    private static TransactionHistory instance;
    private final List<Transaction> transactions;

    private TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public static TransactionHistory getInstance() {
        if (instance == null) {
            instance = new TransactionHistory();
        }
        return instance;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }


    public void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("История транзакций пуста.");
            return;
        }
        System.out.println("\n=== ИСТОРИЯ ТРАНЗАКЦИЙ ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}