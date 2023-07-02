import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {

        private HashMap<String, ArrayList<MonthlyReport>> reports = new HashMap<>();

        public ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();


        public void loadFile(String month, String path) {

                ArrayList<String> content = readFileContents(path);
                String contentString = String.join("\n", content);
                String[] lines = contentString.split("\n");
                ArrayList<MonthlyReport> monthReports = new ArrayList<>();
                for (int i = 1; i < lines.length; i++) {
                        String line = lines[i]; // item_name,is_expense,quantity,unit_price
                        String[] parts = line.split(",");
                        String name = parts[0];
                        boolean isExpense = Boolean.parseBoolean(parts[1]);
                        int quantity = Integer.parseInt(parts[2]);
                        int price = Integer.parseInt(parts[3]);

                        MonthlyReport monthlyReport = new MonthlyReport(name, quantity, price, isExpense, month);
                        monthReports.add(monthlyReport);
                        monthlyReports.add(monthlyReport);
                }
            reports.put(month, monthReports);
        }


        public void monthStatistics() {

            loadFile("01", "m.202101.csv");
            loadFile("02", "m.202102.csv");
            loadFile("03", "m.202103.csv");

            calculateMonthStatistics("01");
            calculateMonthStatistics("02");
            calculateMonthStatistics("03");

        }


        public void calculateMonthStatistics(String month) {
            ArrayList<MonthlyReport> monthlyReports = reports.get(month);
            int maxIncome = 0;
            int maxExpense = 0;
            String profitableItem = "";
            String expensiveItem = "";

            for (MonthlyReport monthlyReport : monthlyReports) {
                int amount = monthlyReport.getQuantity() * monthlyReport.getPrice();

                if (monthlyReport.isExpense()) {
                    if (amount > maxExpense) {
                        maxExpense = amount;
                        expensiveItem = monthlyReport.getName();
                    }
                } else {
                    if (amount > maxIncome) {
                        maxIncome = amount;
                        profitableItem = monthlyReport.getName();
                    }
                }
            }
            System.out.println("Месяц: " + month);
            System.out.println("Самый прибыльный товар: " + profitableItem + ", сумма: " + maxIncome);
            System.out.println("Самая большая трата: " + expensiveItem + ", сумма: " + maxExpense);
            System.out.println();
        }


    public ArrayList<String> readFileContents(String fileName) {
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }
}