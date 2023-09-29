package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    " lastName VARCHAR(100)," +
                    " age TINYINT)");
        } catch (SQLException e) {
            System.out.println("CreateUsersTable Exception");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("DropUsersTable Exception");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SaveUser Exception");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE from users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RemoveUserById Exception");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("GetAllUsers Exception");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement prepareStatement = connection.prepareStatement("TRUNCATE users")) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CleanUsersTable Exception");
        }
    }
}
