package tests.model;

import model.UserModel;
import model.lib.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserModelTest {

    @Test
    void getId() {
        UserModel userModel = new UserModel(1, "John", "Doe", UserRole.STUDENT);
        int id = userModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getFirstName() {
        UserModel userModel = new UserModel(1, "John", "Doe", UserRole.STUDENT);
        String firstName = userModel.getFirstName();
        assertEquals("John", firstName);
    }

    @Test
    void getLastName() {
        UserModel userModel = new UserModel(1, "John", "Doe", UserRole.STUDENT);
        String lastName = userModel.getLastName();
        assertEquals("Doe", lastName);
    }

    @Test
    void getRole() {
        UserModel userModel = new UserModel(1, "John", "Doe", UserRole.STUDENT);
        UserRole role = userModel.getRole();
        assertEquals(UserRole.STUDENT, role);
    }
}
