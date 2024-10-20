package entities;

public class FinancialRecord {
    private int recordID;
    private Employee employee;
    private String recordDate;
    private String description;
    private int amount;
    private String recordType;

    public FinancialRecord(){}

    public FinancialRecord(int recordID, Employee employee, String recordDate, String description, int amount, String recordType) {
        this.recordID = recordID;
        this.employee = employee;
        this.recordDate = recordDate;
        this.description = description;
        this.amount = amount;
        this.recordType = recordType;
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Override
    public String toString() {
        return "FinancialRecord{" +
                "recordID=" + recordID +
                ", employee=" + employee +
                ", recordDate='" + recordDate + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", recordType='" + recordType + '\'' +
                '}';
    }
}
