import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    int sumExpensMonth;
    int sumIncomeMonth;
    int numberOfMonth;
    public ArrayList<MonthlyReportRecord> data = new ArrayList<>(); // {Коньки,TRUE,50,2000}, {Новогодняя ёлка,TRUE,1,100000}

    public MonthlyReport(String path, int nMonth) {
        numberOfMonth = nMonth;
        String fileContents = readFileContentsOrNull(path);

        String[] lines = fileContents.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];                             // {Коньки,TRUE,50,2000}
            String[] parts = line.split(",");

            String itemName = parts[0];                         // {Коньки}
            boolean isExpense = Boolean.parseBoolean(parts[1]); // {TRUE}
            int quantity = Integer.parseInt(parts[2]);          // {50}
            int sum = Integer.parseInt(parts[3]);               // {2000}

            data.add(new MonthlyReportRecord(itemName, isExpense, quantity, sum));
        }
    }
    public void sverkaSumMonth() {

        for (MonthlyReportRecord element : data) {
            if (element.isExpense) {
                sumExpensMonth += element.quantity * element.sum;
            } else {
                sumIncomeMonth += element.quantity * element.sum;
            }
        }
    }
    public void infoMonth() {
        System.out.println("Рассматриваемый месяц: " + numberOfMonth);
        String maxProductName = null;
        int maxProfitProduct = 0;
        String maxExpenceName = null;
        int maxExpence = 0;

        for (MonthlyReportRecord element : data) {
            if (!element.isExpense) {
                if (element.quantity * element.sum > maxProfitProduct) {
                    maxProfitProduct = element.quantity * element.sum;
                    maxProductName = element.itemName;
                }
            } else {
                if (element.quantity * element.sum > maxExpence) {
                    maxExpence = element.quantity * element.sum;
                    maxExpenceName = element.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: " + maxProductName + ". Сумма оставила: " + maxProfitProduct + " руб.");
        System.out.println("Самая большая трата называется: " + maxExpenceName + ". Сумма траты оставила: " + maxExpence + " руб.");
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