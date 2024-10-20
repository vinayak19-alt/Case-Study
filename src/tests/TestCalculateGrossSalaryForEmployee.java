package tests;

import entities.Employee;
import entities.Payroll;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TestCalculateGrossSalaryForEmployee {
    @Test
    public void testCalculateGrossSalaryForEmployee(){
        Employee employee = new Employee(1, "Vinayak", "Sharma", "11-11-2001", "Male", "vinayak@gmail.com", "8126345786", "Bangalore", "SDE", "01-09-2023", null);
        Payroll payroll = new Payroll(1, employee, format(LocalDate.now()), format(LocalDate.now().plusDays(30)), 5000, 1000, 500, 0);
        int grossSalary = payroll.getBasicSalary() + payroll.getOverTimePay();
        assertEquals(6000, grossSalary);
    }


    public String format(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted = date.format(formatter);
        return formatted;
    }

}
