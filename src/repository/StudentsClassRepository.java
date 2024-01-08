package repository;

import model.StudentsClassModel;
import repository.lib.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsClassRepository implements RepositoryInterface {
    public StudentsClassModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM students_classes WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("StudentClass with id: %d does not exist", id));
            } else {
                StudentsClassModel studentsClass = createStudentClassFromResultSet(resultSet);
                connection.close();
                return studentsClass;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentsClassModel> getAllByTeacherId(int teacherId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM students_classes WHERE `teacher_id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("StudentClass with teacherId: %d does not exist", teacherId));
            } else {
                List<StudentsClassModel> studentsClasses = new ArrayList<>();
                while (resultSet.next()) {
                    StudentsClassModel studentsClass = createStudentClassFromResultSet(resultSet);
                    studentsClasses.add(studentsClass);
                }
                connection.close();
                return studentsClasses;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentsClassModel> getByName(String name) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM students_classes WHERE `teacher_id`=? ORDER BY id DESC LIMIT 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("StudentClass with name: %s does not exist", name));
            } else {
                List<StudentsClassModel> studentsClasses = new ArrayList<>();
                while (resultSet.next()) {
                    StudentsClassModel studentsClass = createStudentClassFromResultSet(resultSet);
                    studentsClasses.add(studentsClass);
                }
                connection.close();
                return studentsClasses;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(StudentsClassModel studentsClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `students_classes` (`teacher_id`, `name`) VALUES (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentsClass.getTeacherId());
            preparedStatement.setString(2, studentsClass.getName());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("StudentClass cannot be stored inside database.\nData: %s", studentsClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(StudentsClassModel studentsClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `students_classes` SET `teacher_id`=?, `name`=? WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentsClass.getTeacherId());
            preparedStatement.setString(2, studentsClass.getName());
            preparedStatement.setInt(3, studentsClass.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("StudentClass cannot be updated inside database.\nData: %s", studentsClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(StudentsClassModel studentsClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM students_classes WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentsClass.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("StudentClass cannot be deleted inside database.\nData: %s", studentsClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StudentsClassModel createStudentClassFromResultSet(ResultSet resultSet) throws SQLException {
        return new StudentsClassModel(resultSet.getInt(1),
                resultSet.getInt(1),
                resultSet.getString(2));
    }
}
