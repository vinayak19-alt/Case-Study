package tests;

import dao.TaxService;
import entities.Employee;
import entities.Tax;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVerifyTaxCalculationForHighIncomeEmployee {
    private TaxService taxService;

    @BeforeEach
    void setup(){
        taxService = new TaxService();
    }

    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {

        Employee employee = new Employee(102, "John", "Doe", "15-08-1990", "Male", "john.doe@gmail.com", "9876543210", "New York", "Senior Developer", "01-01-2015", null);
        Tax tax = new Tax(1, employee, 2023, 540000, 108000);

        int taxedIncome = tax.getTaxableIncome();
        int currentTax = (int) (taxedIncome * 0.2);

        assertEquals(tax.getTaxAmount(), currentTax);
    }
}
