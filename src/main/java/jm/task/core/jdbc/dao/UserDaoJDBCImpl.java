package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        String createQuery = "CREATE TABLE IF NOT EXISTS dbtest (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR (45), lastname VARCHAR (45), age TINYINT)";
        try (Connection connection = Util.getConnection();
             PreparedStatement create = connection.prepareStatement(createQuery)) {
            create.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropQuery = "DROP TABLE IF EXISTS dbtest";
        try (Connection connection = Util.getConnection(); PreparedStatement clean = connection.prepareStatement(dropQuery)) {
            clean.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveQuery = "INSERT INTO dbtest(name,lastname,age) VALUES (?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement post = connection.prepareStatement(saveQuery)) {
            post.setString(1, name);
            post.setString(2, lastName);
            post.setByte(3, age);
            post.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeQuery = "DELETE FROM dbtest WHERE id=?";
        try (Connection connection = Util.getConnection(); PreparedStatement delete = connection.prepareStatement(removeQuery)) {
            delete.setLong(1, id);
            delete.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM dbtest");

            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);
                users.add(new User(id, name, lastName, age));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanQuery = "TRUNCATE TABLE dbtest";
        try (Connection connection = Util.getConnection(); PreparedStatement clean = connection.prepareStatement(cleanQuery)) {
            clean.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
