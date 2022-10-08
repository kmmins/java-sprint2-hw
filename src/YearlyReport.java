import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class YearlyReport {
    int numberOfYear;

    public YearlyReportRecord[] data = new YearlyReportRecord[12]; // {01, mRec.expenses, mRec.income}, {02, mRec.expenses, mRec.income}

    public YearlyReport(String path, int nYear){
        numberOfYear = nYear;

        String fileContents = readFileContentsOrNull(path);

        String[] lines = fileContents.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];                                  // [01,1593150,false]
            String[] parts = line.split(",");                  // ["01", "1593150", "false"]

            int month = Integer.parseInt(parts[0]);                  // [01]
            int amount = Integer.parseInt(parts[1]);                 // [1593150]
            boolean isExpense = Boolean.parseBoolean(parts[2]);      // [false]

            if (data[month - 1] == null) {
                data[month - 1] = new YearlyReportRecord(month);
            }
            YearlyReportRecord mRec = data[month - 1];
            if (isExpense) {
                mRec.expenses += amount;
            } else {
                mRec.income += amount;
            }
        }
    }
    public void infoYear() {
        System.out.println("Рассматриваемый год: " + numberOfYear);
        int profitYM;
        int sumProfitYM = 0;
        int sumLossYM = 0;
        int count = 0;

        for (YearlyReportRecord element : data) {
            if (element != null) {
                profitYM = element.income - element.expenses;
                System.out.println("Прибыль в месяце №" + element.month + " равна: " + profitYM + "руб.");
                count += 1;
                sumLossYM += element.expenses;
                sumProfitYM += element.income;
            }
        }
        System.out.println("Средний расход за все месяцы в году равен: " + sumLossYM/count + "руб.");
        System.out.println("Средний доход за все месяцы в году равен: " + sumProfitYM/count + "руб.");
    }
    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}