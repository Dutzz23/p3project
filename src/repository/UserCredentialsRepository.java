package repository;

import model.UserCredentialsModel;
import repository.lib.RepositoryInterface;

import java.sql.*;

public class UserCredentialsRepository implements RepositoryInterface {
    public UserCredentialsModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users_credentials WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("UserCredentials with id: %d does not exist", id));
            } else {
                UserCredentialsModel userCredentials = createUserCredentialsFromResultSet(resultSet);
                connection.close();
                return userCredentials;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserCredentialsModel getByEmail(String email) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users_credentials WHERE `email`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("UserCredentials with email: %s does not exist", email));
            } else {
                UserCredentialsModel userCredentials = createUserCredentialsFromResultSet(resultSet);
                connection.close();
                return userCredentials;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(UserCredentialsModel userCredentials) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `grades`(`user_id`, `password`, `email`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userCredentials.getUserId());
            preparedStatement.setString(2, userCredentials.getEmail());
            preparedStatement.setString(3, userCredentials.getPassword());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("UserCredentials cannot be stored inside database.\nData: %s", userCredentials));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(UserCredentialsModel userCredentials) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `grades` SET  `user_id`=?, `password`=?, `email`=? WHERE `id`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userCredentials.getUserId());
            preparedStatement.setString(2, userCredentials.getEmail());
            preparedStatement.setString(3, userCredentials.getPassword());
            preparedStatement.setInt(4, userCredentials.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("UserCredentials cannot be updated inside database.\nData: %s", userCredentials));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(UserCredentialsModel userCredentials) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM users_credentials WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userCredentials.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("UserCredentials cannot be delete inside database.\nData: %s", userCredentials));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserCredentialsModel createUserCredentialsFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserCredentialsModel(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4));
    }
}
