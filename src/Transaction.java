import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {

        public ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

        public void loadFile(String month, String path) {

                ArrayList<String> content = readFileContents(path);
                String contentString = String.join("\n", content);
                String[] lines = contentString.split("\n");
                for (int i = 1; i < lines.length; i++) {
                        String line = lines[i]; // item_name,is_expense,quantity,unit_price
                        String[] parts = line.split(",");
                        String name = parts[0];
                        boolean isExpense = Boolean.parseBoolean(parts[1]);
                        int quantity = Integer.parseInt(parts[2]);
                        int price = Integer.parseInt(parts[3]);

                        MonthlyReport monthlyReport = new MonthlyReport(name, quantity, price, isExpense, month);
                        monthlyReports.add(monthlyReport);
                }
            }

    public String monthStatistics() {
        HashMap<String, Integer> stats = new HashMap<>(); // String - название товара, Integer = quantity*price, but depending on -if we should decide on whether this total amount is expense or not
        for (MonthlyReport monthlyReport : monthlyReports) {
            stats.put(monthlyReport.name, stats.getOrDefault(monthlyReport.name, 0) + monthlyReport.quantity * monthlyReport.price);// здесь нужно сделать stats.put

            if (monthlyReport.isExpense) {
                // если является тратой, то нужно считать самую большую трату, название товара и сумму
                String monthMaxExpense = null;
                for (String month : stats.keySet()) {
                    if (monthMaxExpense == null) {
                        monthMaxExpense = month;
                        continue;
                    }
                    if (stats.get(monthMaxExpense) < stats.get(month)) {
                        monthMaxExpense = month;
                    }
                }

                String monthSumExpense = null;
                for (String expense : stats.keySet()) {
                    monthSumExpense += expense;
                }

                      /* return monthMaxExpense;
                      return monthSumExpense;
                      return monthlyReport.name; */

                 System.out.println("Максимальный расход - " + monthMaxExpense + ". Сумма расходов - " + monthSumExpense + ". Название товара - " + monthlyReport.name + ".");
            } else {
                // если не является тратой, то это товар, нужно считать самый прибыльный товар, название товара и сумму
                String monthMaxIncome = null;
                for (String month : stats.keySet()) {
                    if (monthMaxIncome == null) {
                        monthMaxIncome = month;
                        continue;
                    }
                    if (stats.get(monthMaxIncome) < stats.get(month)) {
                        monthMaxIncome = month;
                    }
                }

                String monthSumIncome = null;
                for (String income : stats.keySet()) {
                    monthSumIncome += income;
                }

                        /* return monthMaxIncome;
                        return monthSumIncome;
                        return monthlyReport.name; */

                System.out.println("Максимальный доход - " + monthMaxIncome + ". Сумма доходов - " + monthSumIncome + ". Название товара - " + monthlyReport.name + ".");

            }


        }


        return "Нет данных о доходах и расходах.";
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






























//String filePrefix = "";

//for (int i = 1; i <= 3; i++) {
//ArrayList<String> content = readFileContents(filePrefix + "m.20210" + i + ".csv");


            /*
            String filePrefix = "";

            for (int i = 1; i <= 3; i++) {


                String filename = filePrefix + "m.20210" + i + ".csv";
                ArrayList<String> strings = FileReader.readFileContent(filename);
                if (strings.isEmpty()) {
                    System.out.println("Внимание!");
                } */
