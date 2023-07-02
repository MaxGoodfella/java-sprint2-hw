import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Transaction transaction = new Transaction();
        transaction.loadFile("01", "m.202101.csv");
        transaction.loadFile("02", "m.202102.csv");
        transaction.loadFile("03", "m.202103.csv");


        YearTransaction yearTransaction = new YearTransaction();
        yearTransaction.loadFile("y.2021.csv");

        Scanner scanner = new Scanner(System.in);

        YearlyReportEngine engine = new YearlyReportEngine(transaction, yearTransaction);


        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                engine.readMonthlyReport();
                System.out.println("Месячный отчёт считан!");
                System.out.println();
            } else if (userInput == 2) {
                engine.readYearlyReport();
                System.out.println("Годовой отчёт считан!");
                System.out.println();
            } else if (userInput == 3) {
                boolean answer = engine.check();
                System.out.println("Результат проверки: " + answer);
                System.out.println();
            } else if (userInput == 4) {
                System.out.println("Статистика месячных отчётов:");
                transaction.monthStatistics();
            } else if (userInput == 5) {
                System.out.println("Статистика годового отчёта:");
                Statistics statistics = yearTransaction.yearStatistics();
                double averageExpense = statistics.getAvgExpense();
                double averageIncome = statistics.getAvgIncome();
                System.out.println("Средний расход весь год - " + averageExpense + "." + " Средний доход за весь год - " + averageIncome + ".");
                System.out.println();
            } else if (userInput == 6) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить месячные и готовой отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход");
    }
}