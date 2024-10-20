package dao;

import entities.Payroll;

import java.util.List;

public interface IPayrollService {

    //generate payroll info of an employee
    public int generatePayroll(int employeeID, String startDate, String endDate);

    //get payroll by ID
    public void getPayrollByID(int payrollID);

    public Payroll getPayrollsForEmployee(int employeeID);

    public void getPayrollsForPeriod(String startDate, String endDate);
}
