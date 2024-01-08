package tests.repository;

import model.MissedClassModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.MissedClassRepository;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MissedClassRepositoryTest {

    private static MissedClassRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new MissedClassRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        MissedClassModel missedClass = new MissedClassModel(1, 1, 1, 1, Date.valueOf("2023-01-01"), "Details");
        repository.insert(missedClass);

        MissedClassModel retrievedMissedClass = repository.getById(missedClass.getId());

        assertEquals(missedClass, retrievedMissedClass);
    }

    @Test
    void getAllByStudentId() throws ClassNotFoundException {
        int studentId = 1;
        MissedClassModel missedClass1 = new MissedClassModel(1, studentId, 1, 1, Date.valueOf("2023-01-01"), "Details1");
        MissedClassModel missedClass2 = new MissedClassModel(2, studentId, 1, 1, Date.valueOf("2023-01-02"), "Details2");
        repository.insert(missedClass1);
        repository.insert(missedClass2);

        List<MissedClassModel> missedClasses = repository.getAllByStudentId(studentId);

        assertEquals(2, missedClasses.size());
        assertTrue(missedClasses.contains(missedClass1));
        assertTrue(missedClasses.contains(missedClass2));
    }

    @Test
    void getAllByTeacherId() throws ClassNotFoundException {
        int teacherId = 1;
        MissedClassModel missedClass1 = new MissedClassModel(1, 1, 1, teacherId, Date.valueOf("2023-01-01"), "Details1");
        MissedClassModel missedClass2 = new MissedClassModel(2, 1, 1, teacherId, Date.valueOf("2023-01-02"), "Details2");
        repository.insert(missedClass1);
        repository.insert(missedClass2);

        List<MissedClassModel> missedClasses = repository.getAllByTeacherId(teacherId);

        assertEquals(2, missedClasses.size());
        assertTrue(missedClasses.contains(missedClass1));
        assertTrue(missedClasses.contains(missedClass2));
    }

    @Test
    void getAllByGivenDate() throws ClassNotFoundException {
        Date givenDate = Date.valueOf("2023-01-01");
        MissedClassModel missedClass1 = new MissedClassModel(1, 1, 1, 1, givenDate, "Details1");
        MissedClassModel missedClass2 = new MissedClassModel(2, 1, 1, 1, givenDate, "Details2");
        repository.insert(missedClass1);
        repository.insert(missedClass2);

        List<MissedClassModel> missedClasses = repository.getAllByGivenDate(givenDate);

        assertEquals(2, missedClasses.size());
        assertTrue(missedClasses.contains(missedClass1));
        assertTrue(missedClasses.contains(missedClass2));
    }

    @Test
    void insert() throws ClassNotFoundException {
        MissedClassModel missedClass = new MissedClassModel(1, 1, 1, 1, Date.valueOf("2023-01-01"), "Details");
        assertTrue(repository.insert(missedClass));
        MissedClassModel retrievedMissedClass = repository.getById(missedClass.getId());
        assertEquals(missedClass, retrievedMissedClass);
    }

    @Test
    void delete() throws ClassNotFoundException {
        MissedClassModel missedClass = new MissedClassModel(1, 1, 1, 1, Date.valueOf("2023-01-01"), "Details");
        repository.insert(missedClass);

        assertTrue(repository.delete(missedClass));
        assertThrows(Error.class, () -> repository.getById(missedClass.getId()));
    }
}
