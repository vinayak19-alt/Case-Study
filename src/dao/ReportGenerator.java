package dao;

import entities.FinancialRecord;
import entities.Tax;

public class ReportGenerator {
    //class for generating various reports based on payroll, tax and financial record data
    private PayrollService payrollService;
    private TaxService taxService;
    private FinancialRecordService financialRecordService;

    public ReportGenerator(){}

    public ReportGenerator(PayrollService payrollService, TaxService taxService, FinancialRecordService financialRecordService) {
        this.payrollService = payrollService;
        this.taxService = taxService;
        this.financialRecordService = financialRecordService;
    }

    public void generatePayrollReport(int employeeID, String startDate, String endDate){
        int netSalary = payrollService.generatePayroll(employeeID, startDate, endDate);
        System.out.println("The Net Salary for Employee ID: " + employeeID + "  is: " + netSalary);
    }

    public void generateTaxReport(int employeeID, int taxYear){
        taxService.calculateTax(employeeID, taxYear);
    }
    public void generateFinancialRecordsReport(int employeeID) {
        FinancialRecord obj = financialRecordService.getFinancialRecordsForEmployee(employeeID);
        System.out.println(obj);
    }
}
