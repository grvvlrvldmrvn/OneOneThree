package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/oneonethree";
    private static final String USERNAME = "mysql";
    private static final String PASSWORD = "mysql";

    private final Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Ошибка при подключении к базе данных", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void connectionCLose() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseCloseException("Ошибка при закрытии соединения с базой данных", e);
        }
    }
}

class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

class DatabaseCloseException extends RuntimeException {
    public DatabaseCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}