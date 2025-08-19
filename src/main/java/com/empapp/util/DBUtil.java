package com.empapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // DB connection details
    private static final String URL  = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "EMP";
    private static final String PASS = "EMP";

    static {
        try {
            // Load Oracle driver once
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
    }

    // Always return a fresh connection (no static reuse!)
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
