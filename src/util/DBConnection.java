package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private DBConnection(){}
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BankSystem;encrypt=false";
    private static final String USER = "sa";
    private static final String PASS = "123456";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
