package dao;

import exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {
    //class responsible for handling database connections and interactions

    private static final String URL = "jdbc:mysql://localhost:3306/CaseStudy";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty$1234";

    public static Connection getConnection() throws SQLException{
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
    }
}
