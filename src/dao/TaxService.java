package dao;

import JDBC.DatabaseContext;
import exceptions.InvalidInputException;
import exceptions.TaxCalculationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxService implements ITaxService {
    private ValidationService validationService = new ValidationService();
    @Override
    public void calculateTax(int employeeID, int taxYear) {
        String sql = "SELECT * FROM TAX t INNER JOIN Employee e ON e.EmployeeID = t.EmployeeID WHERE e.EmployeeID = ? AND t.TaxYear = ?";
        try(Connection conn = DatabaseContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setInt(2, taxYear);
            ResultSet rs = stmt.executeQuery();
            String name = "";
            int taxAmount = 0;
            int income = 0;
            if(rs.next()){
                name = rs.getString("FirstName");
                income = rs.getInt("TaxableIncome");
                //if we want to fetch the value directly from the DB we can use the below line
                //taxAmount = rs.getInt("TaxAmount");
                taxAmount = (int)(income * 0.2);
                if(!validationService.isValidTaxAmount(taxAmount, income)){
                    throw new InvalidInputException("Tax Amount is invalid");
                }
            }else{
                throw new TaxCalculationException("Tax information for Employee ID: " + employeeID + " does not exist");
            }
            if(taxAmount != 0){
                System.out.println("The tax amount for Employee ID: " + employeeID + " Name: " + name + " is: " + taxAmount);
            }else{
                System.out.println("Either the Employee ID you have entered does not exist or there is no record of the Employee for the entered year");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred T1: " + e.getMessage());
        }
    }

    @Override
    public void getTaxByID(int taxID) {
        String sql = "SELECT * FROM Tax t LEFT JOIN Employee e ON t.EmployeeID = e.EmployeeID WHERE TaxID = ?";
        try(Connection conn = DatabaseContext.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taxID);
            ResultSet rs = stmt.executeQuery();
            String name = "";
            int taxableIncome = 0;
            int taxAmount = 0;
            if(rs.next()){
                name = rs.getString("FirstName");
                taxableIncome = rs.getInt("TaxableIncome");
                taxAmount = rs.getInt("TaxAmount");
            }
            System.out.println("The tax Details for Employee: " + name  + " is: " + "Tax ID: " + taxID + " Taxable Income: " + taxableIncome + " Tax Amount: " + taxAmount);
        } catch (SQLException e) {
            System.out.println("Error Occurred T2: " + e.getMessage());
        }
    }

    @Override
    public void getTaxesForEmployee(int employeeID) {
        String sql = "SELECT * FROM Tax t LEFT JOIN Employee e ON e.EmployeeID = t.EmployeeID WHERE e.EmployeeID = ?";
        try(Connection conn = DatabaseContext.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            String name = "";
            int taxYear = 0;
            int taxID=0;
            int taxableIncome = 0;
            int taxAmount = 0;
            if(rs.next()){
                name = rs.getString("FirstName");
                taxID = rs.getInt("TaxID");
                taxYear = rs.getInt("TaxYear");
                taxableIncome = rs.getInt("TaxableIncome");
                taxAmount = rs.getInt("TaxAmount");
            }
            System.out.println("The tax Details for Employee ID: " + employeeID  + " Name: " + name + " is: " + "Tax ID: " + taxID + " Taxable Income: " + taxableIncome + " Tax Amount: " + taxAmount + " Tax Year: " + taxYear);
        } catch (SQLException e) {
            System.out.println("Error Occurred T3: " + e.getMessage());
        }
    }

    @Override
    public void getTaxesForYear(int taxYear) {
        String sql = "SELECT * FROM Tax t LEFT JOIN Employee e ON e.EmployeeID = t.EmployeeID WHERE TaxYear = ?";
        try(Connection conn = DatabaseContext.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taxYear);
            ResultSet rs = stmt.executeQuery();
            String name = "";
            int id = 0;
            int taxID=0;
            int taxableIncome = 0;
            int taxAmount = 0;
            while(rs.next()){
                name = rs.getString("FirstName");
                taxID = rs.getInt("TaxID");
                id = rs.getInt("EmployeeID");
                taxableIncome = rs.getInt("TaxableIncome");
                taxAmount = rs.getInt("TaxAmount");

                System.out.println("Employee ID: " + id + ", Name: " + name + ", Tax ID: " + taxID + ", Tax Year: " + taxYear + ", Taxable Income: " + taxableIncome + ", Tax Amount: " + taxAmount);
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred T4: " + e.getMessage());
        }
    }

}
