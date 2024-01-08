package repository;

import model.StudentModel;
import repository.lib.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements RepositoryInterface {
    public StudentModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM students WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Student with id: %d does not exist", id));
            } else {
                StudentModel student = createStudentFromResultSet(resultSet);
                connection.close();
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentModel> getAllByStudentsClassId(int studentClassId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM students WHERE students_class_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentClassId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Student with studentClassId: %d does not exist", studentClassId));
            } else {
                List<StudentModel> students = new ArrayList<>();
                while (resultSet.next()) {
                    StudentModel student = createStudentFromResultSet(resultSet);
                    students.add(student);
                }
                connection.close();
                return students;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(StudentModel student) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `students` (`students_class_id`) VALUES (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentsClassId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Student cannot be stored inside database.\nData: %s", student));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(StudentModel student) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `students` SET `students_class_id`=? WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentsClassId());
            preparedStatement.setInt(2, student.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Student cannot be updated inside database.\nData: %s", student));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(StudentModel student) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM students WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Student cannot be deleted inside database.\nData: %s", student));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StudentModel createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new StudentModel(resultSet.getInt(1), resultSet.getInt(2));
    }
}
