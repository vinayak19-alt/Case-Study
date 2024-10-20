package dao;

import entities.Employee;
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
    public Employee getEmployeeByID(int employeeID) {
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
    public void addEmployee(Employee employee) {


        if (!validationService.isValidEmail(employee.getEmail())) {
            throw new InvalidInputException("Invalid email address");
        }

        if (!validationService.isValidPhoneNumber(employee.getPhoneNo())) {
            throw new InvalidInputException("Invalid phone number");
        }
        String sql = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DOB, Gender, Email, PhoneNo, Address, Position, JoiningDate, TerminationDate) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, employee.getEmployeeID());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getDateOfBirth());
            stmt.setString(5, employee.getGender());
            stmt.setString(6, employee.getEmail());
            stmt.setString(7, employee.getPhoneNo());
            stmt.setString(8, employee.getAddress());
            stmt.setString(9, employee.getPosition());
            stmt.setString(10, employee.getJoiningDate());
            stmt.setString(11, employee.getTerminationDate());
            stmt.executeUpdate();
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
