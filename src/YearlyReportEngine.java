import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReportEngine {

    public Transaction transaction;
    public YearTransaction yearTransaction;

    public YearlyReportEngine(Transaction transaction, YearTransaction yearTransaction) {
        this.transaction = transaction;
        this.yearTransaction = yearTransaction;
    }

    public boolean check() {
        // здесь должна быть сверка
        // мне нужно каким-то образом сверить один файл годового отчета с тремя файлами месячных отчётов
        // но проблема в том, что есть доходы и расходы

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
                if (monthlyReport.isExpense) {
                    expensesMonth.put(Integer.parseInt(monthlyReport.month), expensesMonth.getOrDefault(Integer.parseInt(monthlyReport.month), 0) + (monthlyReport.quantity * monthlyReport.price));
                } else {
                    revenueMonth.put(Integer.parseInt(monthlyReport.month), revenueMonth.getOrDefault(Integer.parseInt(monthlyReport.month), 0) + (monthlyReport.quantity * monthlyReport.price));
                }
            }

            for (Integer monthsListed : revenueYear.keySet()) {
                if (!revenueMonth.containsKey(monthsListed)) {
                    System.out.println("Месяц " + monthsListed + " отстутсвует в месячном отчёте");
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
                    System.out.println("Месяц " + monthsMentioned + " отстутсвует в годовом отчёте");
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

    public void readMonthlyReport() {
        String filePrefix = "";

        for (int i = 1; i <= 3; i++) {

            String filename = filePrefix + "m.20210" + i + ".csv";
            ArrayList<String> strings = transaction.readFileContents(filename);
            if (strings.isEmpty()) {
                System.out.println("Внимание!");
            }
        }

        /* transaction.loadFile("January", "resources/m.202101.csv");
        transaction.loadFile("February","resources/m.202102.csv");
        transaction.loadFile("March","resources/m.202103.csv"); */

    }

        public void readYearlyReport () {
            ArrayList<String> lines = yearTransaction.readFileContents("y.2021.csv");
            if (lines.isEmpty()) {
                System.out.println("Внимание!");
            }

            // yearTransaction.loadFile("resources/y.2021.csv");

        }

}

/* HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    FileReader fileReader = new FileReader(); */

/*

    public void readYearlyReport() {

        }



     */


/*
        String filePrefix = "";
        String delimiter = ",";

        for (int i = 1; i <= 3; i++) {

            String filename = filePrefix + "m.20210" + i + ".csv";
            ArrayList<String> strings = FileReader.readFileContent(filename);
            if (strings.isEmpty()) {
                 System.out.println("Внимание!");
            }

            MonthlyReport monthlyReport = new MonthlyReport();

            for (int j = 0; j < strings.size(); j++) {
                String row = strings.get(i);
                String[] splitted = row.split(delimiter);
                Transaction transaction = new Transaction(name, quantity, sum, isExpense);
                transaction.name = splitted[0];
                transaction.isExpense = Boolean.parseBoolean(splitted[1]);
                transaction.quantity = Integer.parseInt(splitted[2]);
                transaction.sum = Integer.parseInt(splitted[3]);

                monthlyReport.rows.add(transaction);

            }

            monthlyReports.put(i, monthlyReport);

        }


                String delimiter = ",";

        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
        if (lines.isEmpty()) {
            System.out.println("Внимание!");
        }

        YearlyReport yearlyReport = new YearlyReport();


        for (int k = 0; k < lines.size(); k++) {
            String row = lines.get(k);
            String[] splitted = row.split(delimiter);
            YearTransaction yearTransaction = new YearTransaction();
            yearTransaction.month = Integer.parseInt(splitted[0]);
            yearTransaction.expenseSum = Integer.parseInt(splitted[1]);
            yearTransaction.incomeSum = Integer.parseInt(splitted[2]);

            //yearlyReport.rowss.add(yearTransaction);
            yearlyReport.months.put(k, yearlyReport);
        }

 */