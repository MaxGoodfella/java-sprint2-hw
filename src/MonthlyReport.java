public class MonthlyReport {

    public String name;
    public int quantity;
    public int price;
    public boolean isExpense;
    public String month;

    public MonthlyReport(String name, int quantity, int price, boolean isExpense, String month) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isExpense = isExpense;
        this.month = month;
    }
}