package tests.repository;

import model.UserCredentialsModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.UserCredentialsRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsRepositoryTest {

    private static UserCredentialsRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new UserCredentialsRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        UserCredentialsModel userCredentials = new UserCredentialsModel(1, 1, "test@example.com", "password");
        repository.insert(userCredentials);

        UserCredentialsModel retrievedUserCredentials = repository.getById(userCredentials.getId());

        assertEquals(userCredentials.getId(), retrievedUserCredentials.getId());
        assertEquals(userCredentials.getUserId(), retrievedUserCredentials.getUserId());
        assertEquals(userCredentials.getEmail(), retrievedUserCredentials.getEmail());
        assertEquals(userCredentials.getPassword(), retrievedUserCredentials.getPassword());
    }

    @Test
    void getByEmail() throws ClassNotFoundException {
        String email = "another@example.com";
        UserCredentialsModel userCredentials = new UserCredentialsModel(1, 2, email, "secure_password");
        repository.insert(userCredentials);

        UserCredentialsModel retrievedUserCredentials = repository.getByEmail(email);

        assertEquals(email, retrievedUserCredentials.getEmail());
        assertEquals(userCredentials.getId(), retrievedUserCredentials.getId());
        assertEquals(userCredentials.getUserId(), retrievedUserCredentials.getUserId());
        assertEquals(userCredentials.getPassword(), retrievedUserCredentials.getPassword());
    }

    @Test
    void insert() throws ClassNotFoundException {
        UserCredentialsModel userCredentials = new UserCredentialsModel(3, 3, "newuser@example.com", "new_password");
        assertTrue(repository.insert(userCredentials));
        assertNotNull(userCredentials.getId());
        UserCredentialsModel retrievedUserCredentials = repository.getById(userCredentials.getId());
        assertEquals(userCredentials.getId(), retrievedUserCredentials.getId());
        assertEquals(userCredentials.getUserId(), retrievedUserCredentials.getUserId());
        assertEquals(userCredentials.getEmail(), retrievedUserCredentials.getEmail());
        assertEquals(userCredentials.getPassword(), retrievedUserCredentials.getPassword());
    }

    @Test
    void update() throws ClassNotFoundException {
        UserCredentialsModel userCredentials = new UserCredentialsModel(4, 4, "update@example.com", "old_password");
        repository.insert(userCredentials);

        UserCredentialsModel updatedUserCredentials = new UserCredentialsModel(userCredentials.getId(), 4, "update@example.com", "new_password");
        assertTrue(repository.update(updatedUserCredentials));

        UserCredentialsModel retrievedUserCredentials = repository.getById(userCredentials.getId());
        assertEquals(userCredentials.getId(), retrievedUserCredentials.getId());
        assertEquals(updatedUserCredentials.getPassword(), retrievedUserCredentials.getPassword());
    }

    @Test
    void delete() throws ClassNotFoundException {
        UserCredentialsModel userCredentials = new UserCredentialsModel(5, 5, "delete@example.com", "password");
        repository.insert(userCredentials);

        assertTrue(repository.delete(userCredentials));
        assertThrows(Error.class, () -> repository.getById(userCredentials.getId()));
    }
}
