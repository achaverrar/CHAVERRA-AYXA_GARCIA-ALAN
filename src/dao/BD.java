package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BD {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./libs"; // H2 database URL
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
