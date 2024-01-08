package repository;

import model.ClassMasterModel;
import repository.lib.RepositoryInterface;

import java.sql.*;

public class ClassMasterRepository implements RepositoryInterface {
    public ClassMasterModel getById(int id) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM class_masters WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Error(String.format("ClassMaster with id: %d does not exist", id));
            } else {
               ClassMasterModel classMaster = createClassMasterFromResultSet(resultSet);
                connection.close();
                return classMaster;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ClassMasterModel createClassMasterFromResultSet(ResultSet resultSet) throws SQLException {
        return new ClassMasterModel();
    }
}
