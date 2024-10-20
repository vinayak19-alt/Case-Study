package dao;

import entities.Employee;

import java.time.LocalDate;

public class ValidationService {
    //class for input validation and business rule enforcement
    // Employee validation methods

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10;
    }

    public boolean isValidTaxAmount(int taxAmount, int taxableIncome) {
        return taxAmount >= 0 && taxAmount <= taxableIncome;
    }


}
