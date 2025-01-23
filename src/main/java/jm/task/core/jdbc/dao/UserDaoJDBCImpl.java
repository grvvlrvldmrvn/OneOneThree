package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTheTable = """
                CREATE TABLE IF NOT EXISTS Users
                              (
                  id       INT AUTO_INCREMENT PRIMARY KEY,
                  name     VARCHAR(50) NOT NULL,
                  lastName VARCHAR(50) NOT NULL,
                  age      INT(3)      NOT NULL
                              );
                """;
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(createTheTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTheTable = """
                DROP TABLE IF EXISTS Users;
                """;
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(dropTheTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertToTable = "INSERT INTO Users (name, lastName, age) " +
                               "VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(insertToTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String deleteFromTable = "DELETE FROM Users WHERE id = " + id;
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(deleteFromTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getAllFromTheTable = "SELECT * FROM Users";
        List<User> userList = new ArrayList<>();
        try {
            User user = new User();
            statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getAllFromTheTable);
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String deleteAllFromTable = "TRUNCATE TABLE Users";
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(deleteAllFromTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}