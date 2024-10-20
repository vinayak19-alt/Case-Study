package tests;

import dao.ValidationService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVerifyErrorHandlingForInvalidEmployeeData {
    ValidationService validationService = new ValidationService();
    @Test
    //test case for invalid phone number
    public void testIsValidEmail_ValidEmail() {
        String validEmail = "vinayak.sharma@gmail.com";
        assertTrue(validationService.isValidEmail(validEmail), "The email should be valid");
    }

    //test case for invalid phone number
    @Test
    public void testIsValidPhoneNumber_ValidPhoneNumber() {
        String validPhoneNumber = "8126345786";
        assertTrue(validationService.isValidPhoneNumber(validPhoneNumber), "The phone number should be valid");
    }
    @Test
    public void testIsValidTaxAmount_ValidTaxAmount() {
        int taxAmount = 5000;
        int taxableIncome = 10000;
        assertTrue(validationService.isValidTaxAmount(taxAmount, taxableIncome), "The tax amount should be valid");
    }

    // Test for invalid tax amount (negative)
    @Test
    public void testIsValidTaxAmount_InvalidTaxAmount_Negative() {
        int taxAmount = -5000; // Negative tax amount
        int taxableIncome = 10000;
        assertFalse(validationService.isValidTaxAmount(taxAmount, taxableIncome), "The tax amount should be invalid");
    }

    // Test for invalid tax amount (greater than taxable income)
    @Test
    public void testIsValidTaxAmount_InvalidTaxAmount_ExceedsTaxableIncome() {
        int taxAmount = 12000; // Greater than taxable income
        int taxableIncome = 10000;
        assertFalse(validationService.isValidTaxAmount(taxAmount, taxableIncome), "The tax amount should be invalid");
    }
}
