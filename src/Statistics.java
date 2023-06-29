public class Statistics {
    private final double avgIncome;
    private final double avgExpense;

    public Statistics(double avgIncome, double avgExpense) {
        this.avgIncome = avgIncome;
        this.avgExpense = avgExpense;
    }

    public double getAvgIncome() {
        return avgIncome;
    }

    public double getAvgExpense() {
        return avgExpense;
    }
}
