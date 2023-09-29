package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД
    public static final String USERNAME = "root";
    public static final String PASSWORD = "rootroot";
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("SQLException");;
        }
        return connection;
    }
}