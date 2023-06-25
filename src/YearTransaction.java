import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class YearTransaction {

        public ArrayList<YearlyReport> yearlyReports = new ArrayList<>();

        public void loadFile(String path) {

            ArrayList<String> content = readFileContents(path);
            String contentString = String.join("\n", content);
            String[] lines = contentString.split("\n");
                for (int i = 1; i < lines.length; i++) {
                   String line = lines[i]; // 02,810000,false
                   String[] parts = line.split(",");
                   int month = Integer.parseInt(parts[0]);
                   int amount = Integer.parseInt(parts[1]);
                   boolean isExpense = Boolean.parseBoolean(parts[2]);

                   YearlyReport yearlyReport = new YearlyReport(month, amount, isExpense);
                   yearlyReports.add(yearlyReport);
                }
        }


        public void yearStatistics () {
            int expenseSum = 0;
            int expenseCount = 0;
            int incomeSum = 0;
            int incomeCount = 0;

            for (YearlyReport yearlyReport : yearlyReports) {
                if (yearlyReport.isExpense) {
                    expenseSum += yearlyReport.amount;
                    expenseCount++;
                } else {
                    incomeSum += yearlyReport.amount;
                    incomeCount++;
                }
            }


            double averageExpense = calculateAverage(expenseSum, expenseCount);
            double averageIncome = calculateAverage(incomeSum, incomeCount);


            }

            public double calculateAverage(int sum, int count){
                if (count == 0) {
                    return 0.0;
                }
                //System.out.println((double) sum / count);
                return (double) sum / count;
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