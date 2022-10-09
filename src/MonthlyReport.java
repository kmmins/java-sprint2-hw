import java.util.ArrayList;

public class MonthlyReport {

    int sumExpensMonth;
    int sumIncomeMonth;
    int numberOfMonth;
    public ArrayList<MonthlyReportRecord> data = new ArrayList<>();

    public MonthlyReport(int nMonth) {
        numberOfMonth = nMonth;
    }
    /**
     * <p>Считает суммы расходов и доходов в месяц необходимые для сверки</p>
     */
    public void sverkaSumMonth() {
        for (MonthlyReportRecord element : data) {
            if (element.isExpense) {
                sumExpensMonth += element.quantity * element.sum;
            } else {
                sumIncomeMonth += element.quantity * element.sum;
            }
        }
    }
    /**
     * <p>Выводит в консоль информацию по считанному месячному отчету</p>
     */
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
    /**
     * <p>Считывает информацию из файла .сsv месячного отчета и преобразует ее в data</p>
     *
     * * @param String path Путь к файлу в папке с программой
     */
    public void loadFromFileContents (String path) {
        String fileContents = ReadFile.readFileContentsOrNull(path);

        String[] lines = fileContents.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");

            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum = Integer.parseInt(parts[3]);

            data.add(new MonthlyReportRecord(itemName, isExpense, quantity, sum));
        }
    }
}