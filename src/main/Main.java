package main;

import dao.*;
import entities.Employee;
import entities.FinancialRecord;
import entities.Payroll;
import entities.Tax;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static EmployeeService employeeService = new EmployeeService();
    static PayrollService payrollService = new PayrollService();
    static FinancialRecordService financialRecordService = new FinancialRecordService();
    static TaxService taxService = new TaxService();
    static ReportGenerator reportGenerator = new ReportGenerator(payrollService, taxService, financialRecordService);

    public static void main(String[] args) {
        System.out.println("Enter your choice: ");
        System.out.println("1. Work with Employee Service");
        System.out.println("2. Work with Payroll Service");
        System.out.println("3. Work with Financial Records");
        System.out.println("4. Work with Taxing Services");
        System.out.println("5. Generate Reports");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                openEmployeeService();
                break;
            case 2:
                openPayrollService();
                break;
            case 3:
                openFinancialRecordService();
                break;
            case 4:
                openTaxService();
                break;
            case 5:
                generateReport();
                break;
            default:
                System.out.println("You entered the wrong choice");
                break;
        }
    }

    private static void openEmployeeService() {
        System.out.println("Enter what service you need:");
        System.out.println("1. Get an Employee using Employee ID");
        System.out.println("2. Get information of all Employees");
        System.out.println("3. Add a new Employee");
        System.out.println("4. Updating the information of Employee");
        System.out.println("5. Removing an Employee");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                getEmployee();
                break;
            case 2:
                getAllEmployees();
                break;
            case 3:
                addEmployee();
                break;
            case 4:
                updateEmployee();
                break;
            case 5:
                removeEmployee();
                break;
            default:
                System.out.println("You entered the wrong choice");
                break;
        }
    }

    private static void getEmployee() {
        System.out.println("Enter the ID of the Employee");
        int id = sc.nextInt();
        Employee retrievedEmployee = employeeService.getEmployeeByID(id);
        System.out.println("Employee ID: " + retrievedEmployee.getEmployeeID() + " Employee First Name: " + retrievedEmployee.getFirstName() + " Employee Last Name : " + retrievedEmployee.getLastName());
    }
    private static void getAllEmployees(){
        System.out.println(employeeService.getAllEmployees());
    }

    private static void addEmployee(){
        System.out.println("Enter Employee ID");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter First Name:");
        String first = sc.nextLine();
        System.out.println("Enter Last Name:");
        String last = sc.nextLine();
        System.out.println("Enter Date of birth:");
        String dob = sc.nextLine();
        System.out.println("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter Phone Number: ");
        String phoneNo = sc.nextLine();
        System.out.println("Enter Address");
        String address = sc.nextLine();
        System.out.println("Enter position: ");
        String position = sc.nextLine();
        System.out.println("Enter Joining Date: ");
        String joining = sc.nextLine();
        System.out.println("Enter Termination Date (or Enter 'null' to leave it as null):");
        String terminationInput = sc.nextLine();
        String termination = terminationInput.equals("null") ? null : terminationInput;
        //ENter in Payroll Table
        System.out.println("Enter the data for Payroll table:");
        System.out.println("Enter the Payroll ID:");
        int payID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Pay Start Date:");
        String startDate = sc.nextLine();
        System.out.println("Enter Pay End Date:");
        String endDate = sc.nextLine();
        System.out.println("Enter Basic Salary:");
        int basicSalary = sc.nextInt();
        System.out.println("Enter overtime pay:");
        int overtimePay = sc.nextInt();
        System.out.println("Enter Deductions:");
        int deductions = sc.nextInt();
        System.out.println("Enter Net Salary:");
        int netSalary = sc.nextInt();
        //Enter in Tax table
        System.out.println("Enter the data for Tax table:");
        System.out.println("Enter Tax ID:");
        int taxID = sc.nextInt();
        System.out.println("Enter Tax year:");
        int taxYear = sc.nextInt();
        Employee newEmployee = new Employee(id, first, last, dob, gender, email, phoneNo, address, position, joining, termination);
        Payroll newPayroll = new Payroll(payID, newEmployee, startDate, endDate, basicSalary, overtimePay, deductions, netSalary);
        Tax newTax = new Tax(taxID, newEmployee, taxYear, (newPayroll.getBasicSalary())*12, (int)(((newPayroll.getBasicSalary())*12)*0.2));
        employeeService.addEmployee(newEmployee, newPayroll, newTax);
    }
    private static void updateEmployee(){
        System.out.println("PLease enter the ID, field and New value respectively to be updated");
        int id = sc.nextInt();
        sc.nextLine();
        String field = sc.nextLine();
        String newValue = sc.nextLine();
        employeeService.updateEmployee(id, field, newValue);
    }
    private static void removeEmployee(){
        System.out.println("Please enter the ID for the employee to be removed");
        int id = sc.nextInt();
        employeeService.removeEmployee(id);
    }

    private static void openPayrollService(){
        System.out.println("Enter your choice: ");
        System.out.println("1. Generate payroll of an Employee");
        System.out.println("2. Get Payroll of an Employee using Payroll ID");
        System.out.println("3. Get Payroll using Employee ID");
        System.out.println("4. Get Payroll according to date");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                generatePayroll();
                break;
            case 2:
                getPayrollForPayrollID();
                break;
            case 3:
                getPayrollForEmployeeID();
                break;
            case 4:
                getPayrollsForPeriod();
                break;
            default:
                System.out.println("You entered a wrong choice");
                break;
        }
    }
    private static void generatePayroll(){
        System.out.println("Enter Employee ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter pay start date:");
        String startDate = sc.nextLine();
        System.out.println("Enter pay end date:");
        String endDate = sc.nextLine();
        int payroll = payrollService.generatePayroll(id, startDate, endDate);
        if(payroll != 0){
            System.out.println("The payroll for the Employee is: " + payroll);
        }else{
            System.out.println("Th payroll for this employee is either not available or the employee ID is wrong");
        }
    }
    private static void getPayrollForPayrollID(){
        System.out.println("Enter Payroll ID");
        int id = sc.nextInt();
        payrollService.getPayrollByID(id);
    }

    private static void getPayrollForEmployeeID(){
        System.out.println("Enter Employee ID:");
        int id = sc.nextInt();
        Payroll obj = payrollService.getPayrollsForEmployee(id);
        System.out.println("The Payroll for EmployeeID " + id + " is: Employee Name: " + obj.getEmployee().getFirstName() + ", Basic Salary: " + obj.getBasicSalary()
                + ", Net Salary: " + obj.getNetSalary());
    }

    private static void getPayrollsForPeriod(){
        System.out.println("Enter start date:");
        String startDate = sc.nextLine();
        System.out.println("Enter end date: ");
        String endDate = sc.nextLine();
        payrollService.getPayrollsForPeriod(startDate, endDate);
    }

    private static void openFinancialRecordService(){
        System.out.println("Enter you choice: ");
        System.out.println("1. Add Financial Record");
        System.out.println("2. Get Financial Record using Record ID");
        System.out.println("3. Get Financial Record using Employee ID");
        System.out.println("4. Get Financial Record using Record Date");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                addRecord();
                break;
            case 2:
                getRecordWithRecordID();
                break;
            case 3:
                getRecordWithEmployeeID();
                break;
            case 4:
                getRecordUsingDates();
                break;
            default:
                System.out.println("You entered wrong choice");
                break;
        }
    }

    private static void addRecord(){
        System.out.println("Enter Employee ID:");
        int empID = sc.nextInt();
        System.out.println("Enter Record ID: ");
        int recID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Description:");
        String desc = sc.nextLine();
        System.out.println("Enter amount:");
        int amount = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Record type:");
        String recType = sc.nextLine();
        financialRecordService.addFinancialRecord(empID, recID, desc, amount, recType);
    }
    private static void getRecordWithRecordID(){
        System.out.println("Please enter the RecordID to get the Financial Records:");
        int recordid = sc.nextInt();
        System.out.println(financialRecordService.getFinacialRecordByID(recordid));
    }
    private static void getRecordWithEmployeeID(){
        System.out.println("Please enter the EmployeeID to find the corresponding Financial Records:");
        int employeeID = sc.nextInt();
        FinancialRecord obj = financialRecordService.getFinancialRecordsForEmployee(employeeID);
        if(obj != null){
            System.out.println(obj);
        }else{
            System.out.println("The EmployeeID you have entered does not exist");
        }
    }
    private static void getRecordUsingDates(){
        System.out.println("Enter the date for which you need records:");
        String date = sc.nextLine();
        FinancialRecord obj = financialRecordService.getFinancialRecordsForDate(date);
        if(obj != null){
            System.out.println(obj);
        }else{
            System.out.println("There are no records for the given date");
        }
    }

    private static void openTaxService(){
        System.out.println("Enter your choice:");
        System.out.println("1. Calculate tax of an Employee");
        System.out.println("2. Get tax details using Tax ID");
        System.out.println("3. Get Tax details using Employee ID");
        System.out.println("4. Get Taxes for Tax year");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                calTax();
                break;
            case 2:
                getTaxUsingTaxID();
                break;
            case 3:
                getTaxUsingEmployeeID();
                break;
            case 4:
                getTaxForTaxYear();
                break;
            default:
                System.out.println("You entered the wrong choice");
                break;
        }
    }
    private static void calTax(){
        System.out.println("Enter the Employee ID: ");
        int id = sc.nextInt();
        System.out.println("Enter the year: ");
        int year = sc.nextInt();
        taxService.calculateTax(id, year);
    }
    private static void getTaxUsingTaxID(){
        System.out.println("Enter Tax ID: ");
        int id = sc.nextInt();
        taxService.getTaxByID(id);
    }
    private static void getTaxUsingEmployeeID(){
        System.out.println("Enter Employee ID: ");
        int id = sc.nextInt();
        taxService.getTaxesForEmployee(id);
    }
    private static void getTaxForTaxYear(){
        System.out.println("Enter tax year: ");
        int year = sc.nextInt();
        taxService.getTaxesForYear(year);
    }

    private static void generateReport(){
        System.out.println("Enter your choice:");
        System.out.println("1. Generate Payroll Report");
        System.out.println("2. Generate Tax Report");
        System.out.println("3. Generate Financial Record Report");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                genPayrollReport();
                break;
            case 2:
                genTaxReport();
                break;
            case 3:
                genFinancialReport();
                break;
            default:
                System.out.println("You entered wrong choice");
                break;
        }
    }
    private static void genPayrollReport(){
        System.out.println("Enter the Employee ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Pay start date:");
        String start = sc.nextLine();
        System.out.println("Enter Pay end date:");
        String end = sc.nextLine();
        reportGenerator.generatePayrollReport(id, start, end);
    }
    private static void genTaxReport(){
        System.out.println("Enter Employee ID:");
        int id = sc.nextInt();
        System.out.println("Enter Tax year:");
        int year = sc.nextInt();
        reportGenerator.generateTaxReport(id, year);
    }
    private static void genFinancialReport(){
        System.out.println("Enter Employee ID:");
        int id =sc.nextInt();
        reportGenerator.generateFinancialRecordsReport(id);
    }
}
