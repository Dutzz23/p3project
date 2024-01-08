package tests.model;

import model.UserCredentialsModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCredentialsModelTest {

    @Test
    void getId() {
        UserCredentialsModel userCredentialsModel = new UserCredentialsModel(1, 2, "password123", "user@example.com");
        int id = userCredentialsModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getUserId() {
        UserCredentialsModel userCredentialsModel = new UserCredentialsModel(1, 2, "password123", "user@example.com");
        int userId = userCredentialsModel.getUserId();
        assertEquals(2, userId);
    }

    @Test
    void getPassword() {
        UserCredentialsModel userCredentialsModel = new UserCredentialsModel(1, 2, "password123", "user@example.com");
        String password = userCredentialsModel.getPassword();
        assertEquals("password123", password);
    }

    @Test
    void getEmail() {
        UserCredentialsModel userCredentialsModel = new UserCredentialsModel(1, 2, "password123", "user@example.com");
        String email = userCredentialsModel.getEmail();
        assertEquals("user@example.com", email);
    }
}
