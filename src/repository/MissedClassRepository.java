package repository;

import model.MissedClassModel;
import repository.lib.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MissedClassRepository implements RepositoryInterface {
    public MissedClassModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM missed_classes WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("MissedClass with id: %d does not exist", id));
            } else {
                MissedClassModel missedClass = createMissedClassFromResultSet(resultSet);
                connection.close();
                return missedClass;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MissedClassModel> getAllByStudentId(int studentId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM missed_classes WHERE student_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("MissedClass with studentId: %d does not exist", studentId));
            } else {
                List<MissedClassModel> missedClasses = new ArrayList<>();
                while (resultSet.next()) {
                    MissedClassModel missedClass = createMissedClassFromResultSet(resultSet);
                    missedClasses.add(missedClass);
                    connection.close();
                }
                return missedClasses;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MissedClassModel> getAllByTeacherId(int teacherId) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM missed_classes WHERE teacher_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("MissedClass with teacherId: %d does not exist", teacherId));
            } else {
                List<MissedClassModel> missedClasses = new ArrayList<>();
                while (resultSet.next()) {
                    MissedClassModel missedClass = createMissedClassFromResultSet(resultSet);
                    missedClasses.add(missedClass);
                    connection.close();
                }
                return missedClasses;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MissedClassModel> getAllByGivenDate(Date givenDate) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM missed_classes WHERE given_date=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (java.sql.Date) givenDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("MissedClass with givenDate: %s does not exist", givenDate));
            } else {
                List<MissedClassModel> missedClasses = new ArrayList<>();
                while (resultSet.next()) {
                    MissedClassModel missedClass = createMissedClassFromResultSet(resultSet);
                    missedClasses.add(missedClass);
                    connection.close();
                }
                return missedClasses;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(MissedClassModel missedClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO `missed_classes` (`student_id`,`subject_id`,`teacher_id`,`given_date`,`details`) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, missedClass.getStudentId());
            preparedStatement.setInt(2, missedClass.getSubjectId());
            preparedStatement.setInt(3, missedClass.getTeacherId());
            preparedStatement.setDate(4, missedClass.getGivenDate());
            preparedStatement.setString(5, missedClass.getDetails());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("MissedClass cannot be stored inside database.\nData: %s", missedClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(MissedClassModel missedClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `missed_classes` SET `student_id`=?, `subject_id`=?,`teacher_id`=?,`given_date`=?,`details`=? WHERE `id`=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, missedClass.getStudentId());
            preparedStatement.setInt(2, missedClass.getSubjectId());
            preparedStatement.setInt(3, missedClass.getTeacherId());
            preparedStatement.setDate(4, missedClass.getGivenDate());
            preparedStatement.setString(5, missedClass.getDetails());
            preparedStatement.setInt(6, missedClass.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("MissedClass cannot be updated inside database.\nData: %s", missedClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(MissedClassModel missedClass) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM missed_classes WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, missedClass.getId());
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new Error(String.format("MissedClass cannot be deleted inside database.\nData: %s", missedClass));
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private MissedClassModel createMissedClassFromResultSet(ResultSet resultSet) throws SQLException {
        return new MissedClassModel(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getDate(5),
                resultSet.getString(6));
    }
}