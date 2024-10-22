package dao;

import JDBC.DatabaseContext;
import entities.Employee;
import entities.Payroll;
import entities.Tax;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IEmployeeService{
    private ValidationService validationService = new ValidationService();

    @Override
    public Employee getEmployeeByID(int employeeID) throws EmployeeNotFoundException {
        if (employeeID <= 0) {
            throw new InvalidInputException("Invalid employee ID");
        }
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
            }else{
                throw new EmployeeNotFoundException("Employee with ID " + employeeID + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred 1 : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<List> getAllEmployees() {
        String sql = "SELECT * FROM EMPLOYEE";

        List list = new ArrayList<>();
        List<List> employees = new ArrayList<>();
        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            int id=0;
            String firstName = "";
            String lastname="";
            String email ="";
            String position="";
            while(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                id = rs.getInt("EmployeeID");
                firstName = rs.getString("Firstname");
                lastname = rs.getString("LastName");
                email = rs.getString("Email");
                position = rs.getString("Position");
                list.add(id);
                list.add(firstName);
                list.add(lastname);
                list.add(email);
                list.add(position);
            }
            employees.add(list);
        } catch (SQLException e) {
            System.out.println("Error Occurred 2: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employee, Payroll payroll, Tax tax) {


        if (!validationService.isValidEmail(employee.getEmail())) {
            throw new InvalidInputException("Invalid email address");
        }

        if (!validationService.isValidPhoneNumber(employee.getPhoneNo())) {
            throw new InvalidInputException("Invalid phone number");
        }
        String employeeSql = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DOB, Gender, Email, PhoneNo, Address, Position, JoiningDate, TerminationDate) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String payrollSql = "INSERT INTO Payroll (PayrollID, EmployeeID, PayStartDate, PayEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String taxSql = "INSERT INTO Tax (TaxID, EmployeeID, TaxYear, TaxableIncome, TaxAmount) " +
                "VALUES (?, ?, ?, ?, ?)";


        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement employeeStmt = conn.prepareStatement(employeeSql);
            PreparedStatement payrollStmt = conn.prepareStatement(payrollSql);
            PreparedStatement taxStmt = conn.prepareStatement(taxSql)){
            //Inserting in Employee Table
            employeeStmt.setInt(1, employee.getEmployeeID());
            employeeStmt.setString(2, employee.getFirstName());
            employeeStmt.setString(3, employee.getLastName());
            employeeStmt.setString(4, employee.getDateOfBirth());
            employeeStmt.setString(5, employee.getGender());
            employeeStmt.setString(6, employee.getEmail());
            employeeStmt.setString(7, employee.getPhoneNo());
            employeeStmt.setString(8, employee.getAddress());
            employeeStmt.setString(9, employee.getPosition());
            employeeStmt.setString(10, employee.getJoiningDate());
            employeeStmt.setString(11, employee.getTerminationDate());
            employeeStmt.executeUpdate();

            // Insert into Payroll table
            payrollStmt.setInt(1, payroll.getPayrollID());
            payrollStmt.setInt(2, employee.getEmployeeID());  // Foreign key from Employee
            payrollStmt.setString(3, payroll.getPayStartDate());
            payrollStmt.setString(4, payroll.getPayEndDate());
            payrollStmt.setInt(5, payroll.getBasicSalary());
            payrollStmt.setInt(6, payroll.getOverTimePay());
            payrollStmt.setInt(7, payroll.getDeductions());
            payrollStmt.setInt(8, payroll.getNetSalary());
            payrollStmt.executeUpdate();

            taxStmt.setInt(1, tax.getTaxID());
            taxStmt.setInt(2, employee.getEmployeeID());  // Foreign key from Employee
            taxStmt.setInt(3, tax.getTaxYear());
            taxStmt.setInt(4, tax.getTaxableIncome());
            taxStmt.setInt(5, tax.getTaxAmount());
            taxStmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error Occurred 3: " + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(int employeeID, String field, String newValue) {
        String sql = "UPDATE Employee SET " + field + " = ? WHERE EmployeeID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newValue);
            stmt.setInt(2, employeeID);
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("The row has been updated successfully");
            }else{
                System.out.println("No record found for EmployeeID: " + employeeID);
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred 4: " + e.getMessage());
        }
    }

    @Override
    public void removeEmployee(int employeeID) {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Occurred 5: " + e.getMessage());
        }
    }
}
