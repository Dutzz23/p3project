package repository;

import model.SubjectModel;
import repository.lib.RepositoryInterface;

import java.sql.*;

public class SubjectRepository implements RepositoryInterface {

    public SubjectModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM subjects WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Subject with id: %d does not exist", id));
            } else {
                SubjectModel subject = createSubjectFromResultSet(resultSet);
                connection.close();
                return subject;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SubjectModel getByName(String name) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM subjects WHERE name=? ORDER BY id DESC LIMIT 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Subject with name: %s does not exist", name));
            } else {
                SubjectModel subject = createSubjectFromResultSet(resultSet);
                connection.close();
                return subject;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(SubjectModel subject) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `subjects`(`name`) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getName());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Subject cannot be stored inside database.\nData: %s", subject));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(SubjectModel subject) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `subjects` SET `name`=? WHERE `id`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Subject cannot be updated inside database.\nData: %s", subject));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(SubjectModel subject) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM subjects WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, subject.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Subject cannot be deleted inside database.\nData: %s", subject));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SubjectModel createSubjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new SubjectModel(resultSet.getInt(1),
                resultSet.getString(2));
    }
}
