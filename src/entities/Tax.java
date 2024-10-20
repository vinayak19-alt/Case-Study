package entities;

public class Tax {
    private int taxID;
    private Employee employee;
    private int taxYear;
    private int taxableIncome;
    private int TaxAmount;

    public Tax(){}

    public Tax(int taxID, Employee employee, int taxYear, int taxableIncome, int taxAmount) {
        this.taxID = taxID;
        this.employee = employee;
        this.taxYear = taxYear;
        this.taxableIncome = taxableIncome;
        TaxAmount = taxAmount;
    }

    public int getTaxID() {
        return taxID;
    }

    public void setTaxID(int taxID) {
        this.taxID = taxID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public int getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(int taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public int getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        TaxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxID=" + taxID +
                ", employee=" + employee +
                ", taxYear=" + taxYear +
                ", taxableIncome=" + taxableIncome +
                ", TaxAmount=" + TaxAmount +
                '}';
    }
}
