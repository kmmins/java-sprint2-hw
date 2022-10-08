public class MonthlyReportRecord {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public int sum;

    public MonthlyReportRecord(String itemName, boolean isExpense, int quantity, int sum) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sum = sum;
    }
}