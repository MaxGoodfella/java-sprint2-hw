import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Transaction transaction = new Transaction();
        transaction.loadFile("January", "resources/m.202101.csv");
        transaction.loadFile("February","resources/m.202102.csv");
        transaction.loadFile("March","resources/m.202103.csv");



        YearTransaction yearTransaction = new YearTransaction();
        // выше был параметр "resources/y.2021.csv" | пока что убрал его

        Scanner scanner = new Scanner(System.in);

        YearlyReportEngine engine = new YearlyReportEngine(transaction, yearTransaction);
        boolean answer = engine.check();


        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1) {

                engine.readMonthlyReport();
                System.out.println("Месячный отчёт считан!");
            } else if (userInput == 2) {

                engine.readYearlyReport();
                System.out.println("Годовой отчёт считан!");
            } else if (userInput == 3) {
                engine.check();
                System.out.println("Результат проверки: " + answer);
            } else if (userInput == 4) {
                System.out.println("Статистика месячных отчётов:");
                System.out.println(transaction.monthStatistics());
                /* String[] monthStats = transaction.monthStatistics();
                System.out.println("Максимальный доход - " + monthStats[0] + ". Сумма доходов - " + monthStats[1] + ". Название товара - " + monthStats[4] + ".");
                System.out.println("Максимальный доход - " + monthStats[2] + ". Сумма доходов - " + monthStats[3] + ". Название товара - " + monthStats[4] + ".");
                 */
            } else if (userInput == 5) {
                System.out.println("Статистика годового отчёта:");
                // System.out.println(yearTransaction.yearStatistics());
                yearTransaction.yearStatistics();
                // System.out.println(year);
            } else if (userInput == 6) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
            // здесь должны быть условные выражения

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

// /Users/MaximGuseynov/dev/second-sprint/java-sprint2-hw/resources
// выше дан абсолютный путь до этих ребят, надо попробовать им сделать