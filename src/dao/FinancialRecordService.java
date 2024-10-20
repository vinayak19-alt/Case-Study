package dao;

import entities.Employee;
import entities.FinancialRecord;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FinancialRecordService implements IFinancialRecordService {

    @Override
    public void addFinancialRecord(int employeeID, int recordID, String description, int amount, String recordType) {
        String sql = "INSERT INTO FinancialRecord (RecordID, EmployeeID, RecordDate, Description, Amount, RecordType) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        /*record date is assigned for today if not given by user*/
        LocalDate obj = LocalDate.now();
        // Define the date format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String recordDate = obj.format(formatter);
        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordID);
            stmt.setInt(2, employeeID);
            stmt.setString(3, recordDate);
            stmt.setString(4, description);
            stmt.setInt(5, amount);
            stmt.setString(6, recordType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Occurred F: " + e.getMessage());
        }
    }

    @Override
    public FinancialRecord getFinacialRecordByID(int recordID) {
        String sql = "SELECT * FROM FinancialRecord f LEFT JOIN Employee e ON f.EmployeeID = e.EmployeeID WHERE RecordID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                return new FinancialRecord(rs.getInt("RecordID"), employee, rs.getString("RecordDate"),
                        rs.getString("Description"), rs.getInt("Amount"), rs.getString("RecordType"));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred F2: " + e.getMessage());
        }
        return null;
    }

    @Override
    public FinancialRecord getFinancialRecordsForEmployee(int employeeID) {
        String sql = "SELECT * FROM Employee e LEFT JOIN FinancialRecord f ON e.EmployeeID = f.EmployeeID WHERE e.EmployeeID = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                return new FinancialRecord(rs.getInt("RecordID"), employee, rs.getString("RecordDate"),
                        rs.getString("Description"), rs.getInt("Amount"), rs.getString("RecordType"));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred F3: " + e.getMessage());
        }
        return null;
    }

    @Override
    public FinancialRecord getFinancialRecordsForDate(String recordDate) {
        String sql = "SELECT * FROM FinancialRecord f LEFT JOIN Employee e On e.EmployeeID = f.EmployeeID WHERE RecordDate = ?";

        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, recordDate);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("DOB"), rs.getString("Gender"), rs.getString("Email"), rs.getString("PhoneNo"),
                        rs.getString("Address"), rs.getString("Position"), rs.getString("JoiningDate"), rs.getString("TerminationDate"));
                return new FinancialRecord(rs.getInt("RecordID"), employee, rs.getString("RecordDate"),
                        rs.getString("Description"), rs.getInt("Amount"), rs.getString("RecordType"));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred F4: " + e.getMessage());
        }
        return null;
    }
}
