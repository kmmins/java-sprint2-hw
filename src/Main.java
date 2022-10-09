import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа помошник Бухгалтера Парка Развлечений");
        ReportsService reportsService = new ReportsService();


        imOutOfHere: while (true) {
            printMenu();
            int commandSwitch = scanner.nextInt();
            switch (commandSwitch) {
                case 1:
                    reportsService.readMR();
                    System.out.println("Месячные отчёты считаны");
                    break;
                case 2:
                    reportsService.readYR();
                    System.out.println("Годовой отчёт считан");
                    break;
                case 3:
                    System.out.println("Сверка отчётов...");
                    reportsService.compareMY();
                    break;
                case 4:
                    System.out.println("Информация о всех месячных отчётах: ");
                    reportsService.showInfoM();
                    break;
                case 5:
                    System.out.println("Информация о годовом отчёте: ");
                    reportsService.showInfoY();
                    break;
                case 6:
                    System.out.println("Выход...");
                    break imOutOfHere;
                default: System.out.println("Извините, такой команды пока нет");
            }
        }
    }
    public static void printMenu () {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход из приложения");
    }
}