import java.util.HashMap;

public class YearlyReportEngine {

    public Transaction transaction;
    public YearTransaction yearTransaction;

    public YearlyReportEngine(Transaction transaction, YearTransaction yearTransaction) {
        this.transaction = transaction;
        this.yearTransaction = yearTransaction;
    }

    public boolean check() {

        boolean check = true;

        if (transaction == null || yearTransaction == null) {
            System.out.println("Сначала необходимо считать данные месячного и годового отчётов");
            check = false;
            return check;
        } else {
            HashMap<Integer, Integer> revenueMonth = new HashMap<>();
            HashMap<Integer, Integer> revenueYear = new HashMap<>();
            HashMap<Integer, Integer> expensesMonth = new HashMap<>();
            HashMap<Integer, Integer> expensesYear = new HashMap<>();

            for (YearlyReport yearlyReport : yearTransaction.yearlyReports) {
                if (yearlyReport.isExpense) {
                    expensesYear.put(yearlyReport.month, yearlyReport.amount); // добавили расходы
                } else {
                    revenueYear.put(yearlyReport.month, yearlyReport.amount); // добавили доходы
                }
            }  // здесь мы наполнили годовые данными по расходам и доходам

            for (MonthlyReport monthlyReport : transaction.monthlyReports) {
                if (monthlyReport.isExpense()) {
                    expensesMonth.put(Integer.parseInt(monthlyReport.getMonth()), expensesMonth.getOrDefault(Integer.parseInt(monthlyReport.getMonth()), 0) + (monthlyReport.getQuantity() * monthlyReport.getPrice()));
                } else {
                    revenueMonth.put(Integer.parseInt(monthlyReport.getMonth()), revenueMonth.getOrDefault(Integer.parseInt(monthlyReport.getMonth()), 0) + (monthlyReport.getQuantity() * monthlyReport.getPrice()));
                }
            }

            for (Integer monthsListed : revenueYear.keySet()) {
                if (!revenueMonth.containsKey(monthsListed)) {
                    System.out.println("Месяц " + monthsListed + " отстутствует в месячном отчёте");
                    check = false;
                    continue;
                }

                int revenueYears = revenueYear.get(monthsListed);
                int revenueMonths = revenueMonth.get(monthsListed);
                if (revenueYears != revenueMonths) {
                    System.out.println("В месяце " + monthsListed + " в годовом отчёте доходы не соответствуют месячному отчёту");
                    System.out.println(revenueYears + "," + revenueMonths);
                    check = false;
                }
            }

            for (Integer monthsMentioned : expensesMonth.keySet()) {
                if (!revenueMonth.containsKey(monthsMentioned)) {
                    System.out.println("Месяц " + monthsMentioned + " отстутствует в годовом отчёте");
                    check = false;
                    continue;
                }

                int expensesYears = expensesYear.get(monthsMentioned);
                int expensesMonths = expensesMonth.get(monthsMentioned);
                if (expensesYears != expensesMonths) {
                    System.out.println("В месяце " + monthsMentioned + " в годовом отчёте расходы не соответствуют месячному отчёту");
                    System.out.println(expensesYears + "," + expensesMonths);
                    check = false;
                }
            }
            return check;
        }
    }
}