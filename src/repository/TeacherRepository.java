package repository;

import model.TeacherModel;
import repository.lib.RepositoryInterface;

import java.sql.*;

public class TeacherRepository implements RepositoryInterface {
    public TeacherModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM teachers WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Teacher with id: %d does not exist", id));
            } else {
                TeacherModel teacher = createTeacherFromResultSet(resultSet);
                connection.close();
                return teacher;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TeacherModel getAllBySubjectId(int subjectId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM teachers WHERE `subject_id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Teacher with subjectId: %d does not exist", subjectId));
            } else {
                TeacherModel teacher = createTeacherFromResultSet(resultSet);
                connection.close();
                return teacher;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(TeacherModel teacher) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `teachers` (`user_id`, `subject_id`) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacher.getUserId());
            preparedStatement.setInt(2, teacher.getSubjectId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Teacher cannot be stored inside database.\nData: %s", teacher));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(TeacherModel teacher) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `teachers` SET `user_id`=?, `subject_id`=? WHERE `id`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacher.getUserId());
            preparedStatement.setInt(2, teacher.getSubjectId());
            preparedStatement.setInt(2, teacher.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Teacher cannot be updated inside database.\nData: %s", teacher));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(TeacherModel teacher) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM teachers WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacher.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Teacher cannot be updated inside database.\nData: %s", teacher));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TeacherModel createTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        return new TeacherModel(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3));
    }
}
