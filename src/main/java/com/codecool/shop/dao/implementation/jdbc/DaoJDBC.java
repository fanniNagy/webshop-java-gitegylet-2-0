package com.codecool.shop.dao.implementation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoJDBC {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        };
        return null;
    }
}
