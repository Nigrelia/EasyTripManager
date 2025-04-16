package com.EasyTripManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Try different URL formats:
    // private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";    // Format 1
    // private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";    // Format 2
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";     // Format 3
    
    private static final String USER = "system";
    private static final String PASSWORD = "281000"; // Replace with your actual password!
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Trying to connect to: " + URL);
            System.out.println("With username: " + USER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Connection failed! Error: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Failed to connect to database", e);
        }
    }
}
