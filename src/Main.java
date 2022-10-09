import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа помошник Бухгалтера Парка Развлечений");
        MonthlyReport[] monthReps = new MonthlyReport[3];
        YearlyReport yearRep = null;

        imOutOfHere: while (true) {
            printMenu();
            int commandSwitch = scanner.nextInt();
            switch (commandSwitch) {
                case 1:
                    for (int i = 0; i < monthReps.length; i++) {
                        monthReps[i] = new MonthlyReport(i+1);
                        monthReps[i].loadFromFileContents("resources/m.20210" + (i + 1) + ".csv");
                    }
                    System.out.println("Месячные отчёты считаны");
                    break;
                case 2:
                    yearRep = new YearlyReport(2021);
                    yearRep.loadFromFileContents("resources/y.2021.csv");
                    System.out.println("Годовой отчёт считан");
                    break;
                case 3:
                    System.out.println("Сверка отчётов...");
                    boolean containsNull = false;
                    for (MonthlyReport element : monthReps) {
                        if (element == null) {
                            containsNull = true;
                            break;
                        }
                    }
                    if (containsNull || yearRep == null) {
                        System.out.println("Ошибка!\r\nДля сверки сначала необходимо считать все месячные и годовые отчеты!\r\nНажмите кнопочки [1] и [2]");
                    } else {
                        boolean sverkaUspeh = true;
                        for (int i = 0; i < monthReps.length; i++) {
                            monthReps[i].sverkaSumMonth();
                            if (monthReps[i].sumExpensMonth != yearRep.data[i].expenses ||
                                    monthReps[i].sumIncomeMonth != yearRep.data[i].income) {
                                sverkaUspeh = false;
                                System.out.println("Обнаружена ошибка! Месяц в котором ошибка: " + (i + 1));
                            }
                        }
                        if (sverkaUspeh) System.out.println("Сверка произведена успешно!");
                    }
                    break;
                case 4:
                    System.out.println("Информация о всех месячных отчётах: ");
                    for (MonthlyReport element : monthReps) {
                        if (element != null) {
                            element.infoMonth();
                        } else {
                            System.out.println("Ошибка!\r\nДля вывода информации о месечных отчетах сначала небходимо считать все месячные!\r\nНажмите кнопочку [1]");
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Информация о годовом отчёте: ");
                    if (yearRep != null) {
                        yearRep.infoYear();
                    } else {
                        System.out.println("Ошибка!\r\nДля вывода информации о годовом отчете сначала небходимо считать все годовые отчеты!\r\nНажмите кнопочку [2]");
                    }
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