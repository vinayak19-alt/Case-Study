package dao;

import entities.Tax;

import java.util.List;

public interface ITaxService {

    public void calculateTax(int employeeID, int taxYear);

    public void getTaxByID(int taxID);

    public void getTaxesForEmployee(int employeeID);

    public void getTaxesForYear(int taxYear);

}
