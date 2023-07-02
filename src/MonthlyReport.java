public class MonthlyReport {
    private final String name;
    private final boolean isExpense;
    private final int quantity;
    private final int price;
    private final String month;

    public MonthlyReport(String name, int quantity, int price, boolean isExpense, String month) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isExpense = isExpense;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getMonth() {
        return month;
    }
}