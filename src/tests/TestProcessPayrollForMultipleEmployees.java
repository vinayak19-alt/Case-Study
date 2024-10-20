package tests;

import dao.DatabaseContext;
import dao.PayrollService;
import entities.Employee;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProcessPayrollForMultipleEmployees {

    private PayrollService payrollService;
    private List<Employee> employees;

    @Before
    public void setup() {
        payrollService = new PayrollService();

        // Setup mock employee data
        employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Doe", "01-01-1990", "Male", "john.doe@example.com", "1234567890", "New York", "Developer", "01-01-2020", null));
        employees.add(new Employee(2, "Jane", "Smith", "15-05-1992", "Female", "jane.smith@example.com", "0987654321", "Los Angeles", "Manager", "01-01-2021", null));
        employees.add(new Employee(3, "Sam", "Brown", "10-10-1985", "Male", "sam.brown@example.com", "1122334455", "Chicago", "Tester", "01-01-2019", null));

        try (Connection conn = DatabaseContext.getConnection()) {
            String sql = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DOB, Gender, Email, PhoneNo, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            for (Employee employee : employees) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while inserting mock data into the database.");
        }
    }

    @Test
    public void testProcessPayrollForMultipleEmployees() {
        List<Integer> employeeIDs = new ArrayList<>();
        employeeIDs.add(1);
        employeeIDs.add(2);
        employeeIDs.add(3);
        String startDate = "01-10-2024";
        String endDate = "31-10-2024";

        // Act
        List<Integer> netSalaries = new ArrayList<>();
        for (int employeeID : employeeIDs) {
            int netSalary = payrollService.generatePayroll(employeeID, startDate, endDate);
            netSalaries.add(netSalary);
        }

        // Assert
        assertEquals(employeeIDs.size(), netSalaries.size());

        for (int netSalary : netSalaries) {
            assertTrue("Net salary should be non-negative", netSalary >= 0);
        }
    }
}
