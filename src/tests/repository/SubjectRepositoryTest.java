package tests.repository;

import model.SubjectModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.SubjectRepository;

import static org.junit.jupiter.api.Assertions.*;

class SubjectRepositoryTest {

    private static SubjectRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new SubjectRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        SubjectModel subject = new SubjectModel(1, "Math");
        repository.insert(subject);

        SubjectModel retrievedSubject = repository.getById(subject.getId());

        assertEquals(subject.getId(), retrievedSubject.getId());
        assertEquals(subject.getName(), retrievedSubject.getName());
    }

    @Test
    void getByName() throws ClassNotFoundException {
        String name = "Physics";
        SubjectModel subject = new SubjectModel(1, name);
        repository.insert(subject);

        SubjectModel retrievedSubject = repository.getByName(name);

        assertEquals(name, retrievedSubject.getName());
        assertEquals(subject.getId(), retrievedSubject.getId());
    }

    @Test
    void insert() throws ClassNotFoundException {
        SubjectModel subject = new SubjectModel(1000,"Chemistry");
        assertTrue(repository.insert(subject));
        assertNotNull(subject.getId());
        SubjectModel retrievedSubject = repository.getById(subject.getId());
        assertEquals(subject.getId(), retrievedSubject.getId());
        assertEquals(subject.getName(), retrievedSubject.getName());
    }

    @Test
    void update() throws ClassNotFoundException {
        SubjectModel subject = new SubjectModel(1000,"Biology");
        repository.insert(subject);

        SubjectModel updatedSubject = new SubjectModel(subject.getId(), "Updated Biology");
        assertTrue(repository.update(updatedSubject));

        SubjectModel retrievedSubject = repository.getById(subject.getId());
        assertEquals(subject.getId(), retrievedSubject.getId());
        assertEquals(updatedSubject.getName(), retrievedSubject.getName());
    }

    @Test
    void delete() throws ClassNotFoundException {
        SubjectModel subject = new SubjectModel(1000,"History");
        repository.insert(subject);

        assertTrue(repository.delete(subject));
        assertThrows(Error.class, () -> repository.getById(subject.getId()));
    }
}
