package repository;

import model.GradeModel;
import repository.lib.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepository implements RepositoryInterface {
    public GradeModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM grades WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Grade with id: %d does not exist", id));
            } else {
                GradeModel grade = createGradeFromResultSet(resultSet);
                connection.close();
                return grade;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GradeModel> getAllByStudentIdAndSubjectId(int studentId, int subjectId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM grades WHERE student_id=? AND subject_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Grade with studentId: %d and subjectId: %d does not exist", studentId, subjectId));
            } else {
                List<GradeModel> grades = new ArrayList<>();
                while (resultSet.next()) {
                    GradeModel grade = createGradeFromResultSet(resultSet);
                    grades.add(grade);
                }
                connection.close();
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GradeModel> getAllByStudentIdAndTeacherId(int studentId, int teacherId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM grades WHERE student_id=? AND teacher_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("Grade with studentId: %d and teacherId: %d does not exist", studentId, teacherId));
            } else {
                List<GradeModel> grades = new ArrayList<>();
                while (resultSet.next()) {
                    GradeModel grade = createGradeFromResultSet(resultSet);
                    grades.add(grade);
                }
                connection.close();
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(GradeModel grade) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `grades`(`student_id`, `subject_id`, `teacher_id`, `value`, `grade_given_data`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getSubjectId());
            preparedStatement.setInt(3, grade.getTeacherId());
            preparedStatement.setFloat(4, grade.getValue());
            preparedStatement.setDate(5, grade.getGradeGivenData());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Grade cannot be stored inside database.\nData: %s", grade));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(GradeModel grade) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `grades` SET `student_id` = ?, `subject_id`=?, `teacher_id`=?, `value`=?, `grade_given_data`=? WHERE `id`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getSubjectId());
            preparedStatement.setInt(3, grade.getTeacherId());
            preparedStatement.setFloat(4, grade.getValue());
            preparedStatement.setDate(5, grade.getGradeGivenData());
            preparedStatement.setInt(6, grade.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Grade cannot be updated inside database.\nData: %s", grade));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(GradeModel grade) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM grades WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, grade.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("Grade cannot be deleted inside database.\nData: %s", grade));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private GradeModel createGradeFromResultSet(ResultSet resultSet) throws SQLException {
        return new GradeModel(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getDate(6));
    }
}
