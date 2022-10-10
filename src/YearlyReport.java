public class YearlyReport {
    int numberOfYear;
    public YearlyReportRecord[] data = new YearlyReportRecord[12];
    public YearlyReport(int nYear) {
        numberOfYear = nYear;
    }
    /**
     * <p>Выводит в консоль информацию по считанному годовому отчету</p>
     */
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
        System.out.println("Средний расход за все месяцы в году равен: " + sumLossYM / count + "руб.");
        System.out.println("Средний доход за все месяцы в году равен: " + sumProfitYM / count + "руб.");
    }

    /**
     * <p>Считывает информацию из файла .сsv годового отчета и преобразует ее в data</p>
     *
     * * @param String path Путь к файлу в папке с программой
     */
    public void loadFromFileContents (String path) {
        String fileContents = ReadFile.readFileContentsOrNull(path);
        String[] lines = fileContents.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");

            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

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
}