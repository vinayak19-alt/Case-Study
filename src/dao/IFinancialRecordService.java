package dao;

import entities.FinancialRecord;

public interface IFinancialRecordService {

    public void addFinancialRecord(int employeeID,int recordID, String description, int amount, String recordType);

    public FinancialRecord getFinacialRecordByID(int recordID);

    public FinancialRecord getFinancialRecordsForEmployee(int employeeID);

    public FinancialRecord getFinancialRecordsForDate(String recordDate);
}
