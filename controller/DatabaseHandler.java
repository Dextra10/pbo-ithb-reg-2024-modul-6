package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import javax.swing.JOptionPane;

public class DatabaseHandler {

    public Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/test?serverTimezone=" + TimeZone.getDefault().getID();
    private String username = "root";
    private String password = "";

    @SuppressWarnings("deprecation")
    private void logOn() throws SQLException {
        try {
            // Load JDBC Driver
            Class.forName(driver).newInstance();
            // Make Object Connection
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // Handle Errors
            System.out.println("Error occurred when loading JDBC driver: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error occurred when loading JDBC driver: " + ex.getMessage());
            throw new SQLException("Error occurred when loading JDBC driver", ex);
        } catch (SQLException ex) {
            // Handle SQL Errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage());
            throw ex;
        }
    }

    private void logOff() {
        try {
            // Close Connection
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error occurred when closing connection: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error occurred when closing connection: " + ex.getMessage());
        }

    }

    public void connect() {
        try {
            logOn();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }

    }

    public void disconnect() {
        try {
            logOff();   
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }
}