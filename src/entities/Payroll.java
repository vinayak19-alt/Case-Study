package entities;

public class Payroll {
    private int payrollID;
    private Employee employee;
    private String payStartDate;
    private String payEndDate;
    private int BasicSalary;
    private int overTimePay;
    private int deductions;
    private int netSalary;

    public Payroll(){}

    public Payroll(int payrollID, Employee employee, String payStartDate, String payEndDate, int basicSalary, int overTimePay, int deductions, int netSalary) {
        this.payrollID = payrollID;
        this.employee = employee;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        BasicSalary = basicSalary;
        this.overTimePay = overTimePay;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(String payStartDate) {
        this.payStartDate = payStartDate;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public int getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        BasicSalary = basicSalary;
    }

    public int getOverTimePay() {
        return overTimePay;
    }

    public void setOverTimePay(int overTimePay) {
        this.overTimePay = overTimePay;
    }

    public int getDeductions() {
        return deductions;
    }

    public void setDeductions(int deductions) {
        this.deductions = deductions;
    }

    public int getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(int netSalary) {
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollID=" + payrollID +
                ", employee=" + employee +
                ", payStartDate='" + payStartDate + '\'' +
                ", payEndDate='" + payEndDate + '\'' +
                ", BasicSalary=" + BasicSalary +
                ", overTimePay=" + overTimePay +
                ", deductions=" + deductions +
                ", netSalary=" + netSalary +
                '}';
    }
}
