package repository;

import model.UserModel;
import model.lib.UserRole;
import repository.lib.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface {
    public UserModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("User with id: %d does not exist", id));
            } else {
                UserModel user = createUserFromResultSet(resultSet);
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel getByFirstAndLastName(String firstName, String lastName) throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users WHERE `last_name`=? AND `first_name`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new Error(String.format("User with first name: %s and last name: %s does not exist", firstName, lastName));
            } else {
                UserModel user = createUserFromResultSet(resultSet);
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserModel> getAllByByRole(UserRole userRole) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users WHERE `role`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userRole.value);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("UserCredentials with role: %s does not exist", userRole.name()));
            } else {
                List<UserModel> users = new ArrayList<>();
                while (resultSet.next()) {
                    UserModel user = createUserFromResultSet(resultSet);
                    users.add(user);
                }
                connection.close();
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(UserModel user) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `users`(`first_name`, `last_name`, `role`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getRole().value);
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("User cannot be stored inside database.\nData: %s", user));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(UserModel user) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `users` SET `first_name`=?, `last_name`=?, `role`=? where `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getRole().value);
            preparedStatement.setInt(4, user.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("User cannot be updated inside database.\nData: %s", user));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean delete(UserModel user)throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("User cannot be deleted inside database.\nData: %s", user));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserRole getUserRoleByCode(int code) {
        return switch (code) {
            case 1 -> UserRole.STUDENT;
            case 2 -> UserRole.TEACHER;
            case 3 -> UserRole.HEADMASTER;
            case 4 -> UserRole.ADMIN;
            default -> UserRole.UNKNOWN;
        };
    }

    private UserModel createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserModel(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                getUserRoleByCode(resultSet.getInt(3)));
    }
}


