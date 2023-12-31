import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Transaction transaction = new Transaction();

        YearTransaction yearTransaction = new YearTransaction();

        Scanner scanner = new Scanner(System.in);

        YearlyReportEngine engine = new YearlyReportEngine(transaction, yearTransaction);

        boolean isTransactionRead = false; // Артём, я надеюсь, ты имел в виду то, что я сделал
        boolean isYearTransactionRead = false; // Если нет, то можешь поподробнее объяснить, пожалуйста :)

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                transaction.loadFile("01", "m.202101.csv");
                transaction.loadFile("02", "m.202102.csv");
                transaction.loadFile("03", "m.202103.csv");
                System.out.println("Месячный отчёт считан!");
                System.out.println();
                isTransactionRead = true;
            } else if (userInput == 2) {
                yearTransaction.loadFile("y.2021.csv");
                System.out.println("Годовой отчёт считан!");
                System.out.println();
                isYearTransactionRead = true;
            } else if (userInput == 3) {
                if (isTransactionRead && isYearTransactionRead) {
                    boolean answer = engine.check();
                    System.out.println("Результат проверки: " + answer);
                    System.out.println();
                } else {
                    System.out.println("Пожалуйста, выполните пункты 1 и 2 перед выполнением пункта 3");
                }
            } else if (userInput == 4) {
                if (isTransactionRead) {
                    System.out.println("Статистика месячных отчётов:");
                    transaction.calculateMonthStatistics("01");
                    transaction.calculateMonthStatistics("02");
                    transaction.calculateMonthStatistics("03");
                } else {
                    System.out.println("Пожалуйста, выполните пункт 1 перед выполнением пункта 4");
                }
            } else if (userInput == 5) {
                if (isYearTransactionRead) {
                    System.out.println("Статистика годового отчёта:");
                    Statistics statistics = yearTransaction.yearStatistics();
                    double averageExpense = statistics.getAvgExpense();
                    double averageIncome = statistics.getAvgIncome();
                    System.out.println("Средний расход весь год - " + averageExpense + "." + " Средний доход за весь год - " + averageIncome + ".");
                    System.out.println();
                } else {
                    System.out.println("Пожалуйста, выполните пункт 2 перед выполнением пункта 5");
                }
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