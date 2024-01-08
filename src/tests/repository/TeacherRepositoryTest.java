package tests.repository;

import model.TeacherModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TeacherRepository;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {

    private static TeacherRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new TeacherRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        TeacherModel teacher = new TeacherModel(1, 1, 1);
        repository.insert(teacher);

        TeacherModel retrievedTeacher = repository.getById(teacher.getId());

        assertEquals(teacher.getId(), retrievedTeacher.getId());
        assertEquals(teacher.getUserId(), retrievedTeacher.getUserId());
        assertEquals(teacher.getSubjectId(), retrievedTeacher.getSubjectId());
    }

    @Test
    void getAllBySubjectId() throws ClassNotFoundException {
        int subjectId = 1;
        TeacherModel teacher1 = new TeacherModel(1, 1, subjectId);
        TeacherModel teacher2 = new TeacherModel(2, 2, subjectId);
        repository.insert(teacher1);
        repository.insert(teacher2);

        TeacherModel retrievedTeacher = repository.getAllBySubjectId(subjectId);

        assertEquals(subjectId, retrievedTeacher.getSubjectId());
        assertTrue(retrievedTeacher.getUserId() == teacher1.getUserId() || retrievedTeacher.getUserId() == teacher2.getUserId());
    }

    @Test
    void insert() throws ClassNotFoundException {
        TeacherModel teacher = new TeacherModel(1, 1, 1);
        assertTrue(repository.insert(teacher));
        assertNotNull(teacher.getId());
        TeacherModel retrievedTeacher = repository.getById(teacher.getId());
        assertEquals(teacher.getId(), retrievedTeacher.getId());
        assertEquals(teacher.getUserId(), retrievedTeacher.getUserId());
        assertEquals(teacher.getSubjectId(), retrievedTeacher.getSubjectId());
    }

    @Test
    void update() throws ClassNotFoundException {
        TeacherModel teacher = new TeacherModel(1, 1, 1);
        repository.insert(teacher);

        TeacherModel updatedTeacher = new TeacherModel(teacher.getId(), 2, 2); // Creează un profesor actualizat
        assertTrue(repository.update(updatedTeacher));

        TeacherModel retrievedTeacher = repository.getById(teacher.getId());
        assertEquals(teacher.getId(), retrievedTeacher.getId());
        assertEquals(updatedTeacher.getUserId(), retrievedTeacher.getUserId());
        assertEquals(updatedTeacher.getSubjectId(), retrievedTeacher.getSubjectId());
    }

    @Test
    void delete() throws ClassNotFoundException {
        TeacherModel teacher = new TeacherModel(1, 1, 1);
        repository.insert(teacher);

        assertTrue(repository.delete(teacher));
        assertThrows(Error.class, () -> repository.getById(teacher.getId()));
    }
}
