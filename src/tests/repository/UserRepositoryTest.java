package tests.repository;

import model.UserModel;
import model.lib.UserRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static UserRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new UserRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        UserModel user = new UserModel(1, "John", "Doe", UserRole.STUDENT);
        repository.insert(user);

        UserModel retrievedUser = repository.getById(user.getId());

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    @Test
    void getByFirstAndLastName() throws ClassNotFoundException {
        String firstName = "Jane";
        String lastName = "Smith";
        UserModel user = new UserModel(2, firstName, lastName, UserRole.TEACHER);
        repository.insert(user);

        UserModel retrievedUser = repository.getByFirstAndLastName(firstName, lastName);

        assertEquals(user, retrievedUser);
    }

    @Test
    void getAllByRole() throws ClassNotFoundException {
        UserRole userRole = UserRole.TEACHER;
        UserModel user1 = new UserModel(3, "Bob", "Johnson", userRole);
        UserModel user2 = new UserModel(4, "Alice", "Williams", userRole);
        repository.insert(user1);
        repository.insert(user2);

        List<UserModel> retrievedUsers = repository.getAllByByRole(userRole);

        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(user1));
        assertTrue(retrievedUsers.contains(user2));
    }

    @Test
    void insert() throws ClassNotFoundException {
        UserModel user = new UserModel(5, "Michael", "Davis", UserRole.HEADMASTER);
        assertTrue(repository.insert(user));

        assertNotNull(user.getId());
        UserModel retrievedUser = repository.getById(user.getId());

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    @Test
    void update() throws ClassNotFoundException {
        UserModel user = new UserModel(6, "Eva", "Miller", UserRole.STUDENT);
        repository.insert(user);

        UserModel updatedUser = new UserModel(user.getId(), "Eva", "Smith", UserRole.STUDENT);
        assertTrue(repository.update(updatedUser));

        UserModel retrievedUser = repository.getById(user.getId());
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(updatedUser.getLastName(), retrievedUser.getLastName());
    }

    @Test
    void delete() throws ClassNotFoundException {
        UserModel user = new UserModel(7, "James", "Brown", UserRole.ADMIN);
        repository.insert(user);

        assertTrue(repository.delete(user));
        assertThrows(Error.class, () -> repository.getById(user.getId()));
    }
}
