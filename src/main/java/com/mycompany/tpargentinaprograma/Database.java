package com.mycompany.tpargentinaprograma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/tpargentinaprograma";
    private static final String USER = "tpArgProgramaAdmin";
    private static final String PASSWORD = "JavaArgPrograma.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
