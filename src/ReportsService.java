public class ReportsService {

    MonthlyReport[] monthReps = new MonthlyReport[3];
    YearlyReport yearRep = null;

    public void readMR() {
        for (int i = 0; i < monthReps.length; i++) {
            monthReps[i] = new MonthlyReport(i + 1);
            monthReps[i].loadFromFileContents("resources/m.20210" + (i + 1) + ".csv");
        }
    }

    public void readYR() {
        yearRep = new YearlyReport(2021);
        yearRep.loadFromFileContents("resources/y.2021.csv");
    }

    public void compareMY() {
        boolean containsNull = false;
        for (MonthlyReport element : monthReps) {
            if (element == null) {
                containsNull = true;
                break;
            }
        }
        if (containsNull || yearRep == null) {
            System.out.println("Ошибка!\r\nДля сверки сначала необходимо считать все месячные и годовые отчеты!" +
                    "\r\nНажмите кнопочки [1] и [2]");
        } else {
            boolean sverkaUspeh = true;
            for (int i = 0; i < monthReps.length; i++) {
                monthReps[i].sverkaSumMonth();
                if (monthReps[i].sumExpenseMonth != yearRep.data[i].expenses ||
                        monthReps[i].sumIncomeMonth != yearRep.data[i].income) {
                    sverkaUspeh = false;
                    System.out.println("Обнаружена ошибка! Месяц в котором ошибка: " + (i + 1));
                }
            }
            if (sverkaUspeh) System.out.println("Сверка произведена успешно!");
        }
    }

    public void showInfoM() {
        for (MonthlyReport element : monthReps) {
            if (element != null) {
                element.infoMonth();
            } else {
                System.out.println("Ошибка!" +
                        "\r\nДля вывода информации о месячных отчетах сначала необходимо считать все месячные!" +
                        "\r\nНажмите кнопочку [1]");
                break;
            }
        }
    }

    public void showInfoY() {
        if (yearRep != null) {
            yearRep.infoYear();
        } else {
            System.out.println("Ошибка!" +
                    "\r\nДля вывода информации о годовом отчете сначала необходимо считать все годовые отчеты!" +
                    "\r\nНажмите кнопочку [2]");
        }
    }
}