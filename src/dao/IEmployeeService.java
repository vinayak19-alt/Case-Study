package dao;

import entities.Employee;

import java.util.List;

public interface IEmployeeService {

    //fetching the employee ID
    public Employee getEmployeeByID(int employeeID);

    //fetching all the employees working in the organisation
    public List<List> getAllEmployees();

    //adding employee to db
    public void addEmployee(Employee employee);

    //updating the data of an employee
    public void updateEmployee(int employeeID, String field, String newValue);

    //delete an employee form db
    public void removeEmployee(int employeeID);
}