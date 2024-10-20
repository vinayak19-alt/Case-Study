package dao;

import entities.Employee;
import entities.Payroll;
import exceptions.InvalidInputException;
import exceptions.PayrollGenerationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PayrollService implements IPayrollService {

    @Override
    public int generatePayroll(int employeeID, String startDate, String endDate) {
        String sql = "SELECT * FROM Payroll p LEFT JOIN Employee e ON e.EmployeeID = p.EmployeeID WHERE e.EmployeeID = ? AND p.PayStartDate <= ? AND p.PayEndDate >= ?";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);


        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            int basicSalary =0;
            int overtimePay = 0;
            int deductions = 0;
            if(rs.next()){
                basicSalary = rs.getInt("BasicSalary");
                overtimePay = rs.getInt("OvertimePay");
                deductions = rs.getInt("Deductions");
            }
//            else{
//                throw new PayrollGenerationException("Payroll for Employee ID: " + employeeID + " cannot be generated");
//            }
            int netSalary = (basicSalary + overtimePay) - deductions;
            return netSalary;
        } catch (SQLException e) {
            System.out.println("Error Occurred P1: " + e.getMessage());
        }
        return 0;
    }

    public int generatePayroll(int basicSalary, int overtimePay, int deductions) {
        int netSalary = (basicSalary + overtimePay) - deductions;
        return netSalary;
    }
    @Override
    public void getPayrollByID(int payrollID) {
        if(payrollID < 0){
            throw new InvalidInputException("Payroll Id entered is invalid");
        }
        String sql = "SELECT * FROM Payroll p LEFT JOIN Employee e ON e.EmployeeID = p.EmployeeID WHERE PayrollID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payrollID);
            ResultSet rs = stmt.executeQuery();
            int employeeID = 0;
            int basicSalary = 0;
            int deductions =0;
            int overtimePay = 0;
            int netSalary = 0;
            if(rs.next()){
                employeeID = rs.getInt("EmployeeID");
                basicSalary = rs.getInt("BasicSalary");
                deductions = rs.getInt("Deductions");
                overtimePay = rs.getInt("OvertimePay");
                netSalary = rs.getInt("NetSalary");
            }
            System.out.println("The Payroll details for Employee ID: " + employeeID + " with Payroll ID: " + payrollID + ", is Basic Salary: " +
                    basicSalary + ", Overtime Pay: " + overtimePay + ", Deductions: " + deductions + " and Net Salary: " + netSalary);
        } catch (SQLException e) {
            System.out.println("Error Occurred P2: " + e.getMessage());
        }
    }

    @Override
    public Payroll getPayrollsForEmployee(int employeeID) {
        String sql = "SELECT * FROM Employee e RIGHT JOIN Payroll p ON e.EmployeeID = p.EmployeeID WHERE e.EmployeeID = ?";

        try(Connection conn = DatabaseContext.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                return new Payroll(rs.getInt("PayrollID"), employee, rs.getString("PayStartDate"),
                        rs.getString("PayEndDate"), rs.getInt("BasicSalary"), rs.getInt("OvertimePay"),
                        rs.getInt("Deductions"), rs.getInt("NetSalary"));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred P3: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void getPayrollsForPeriod(String startDate, String endDate) {
        String sql = "SELECT * FROM Payroll p LEFT JOIN Employee e ON p.EmployeeID = e.EmployeeID WHERE p.PayStartDate >= ? AND p.PayEndDate <= ?";
        try(Connection conn = DatabaseContext.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();
            int id =0;
            int netSalary =0;
            String name="";
            while(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                id = employee.getEmployeeID();
                netSalary = rs.getInt("NetSalary");
                name = employee.getFirstName();
                System.out.println("The payrolls between the date " + startDate + " and " + endDate + " is/are: " + id + " " + name + " " + netSalary);
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred P4: " + e.getMessage());
        }
    }

}
