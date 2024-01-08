package tests.repository;

import model.StudentsClassModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.StudentsClassRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentsClassRepositoryTest {

    private static StudentsClassRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new StudentsClassRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        StudentsClassModel studentsClass = new StudentsClassModel(1, 1, "Class A");
        repository.insert(studentsClass);

        StudentsClassModel retrievedStudentsClass = repository.getById(studentsClass.getId());

        assertEquals(studentsClass.getId(), retrievedStudentsClass.getId());
        assertEquals(studentsClass.getTeacherId(), retrievedStudentsClass.getTeacherId());
        assertEquals(studentsClass.getName(), retrievedStudentsClass.getName());
    }

    @Test
    void getAllByTeacherId() throws ClassNotFoundException {
        int teacherId = 2;
        StudentsClassModel studentsClass1 = new StudentsClassModel(2, teacherId, "Class B");
        StudentsClassModel studentsClass2 = new StudentsClassModel(3, teacherId, "Class C");
        repository.insert(studentsClass1);
        repository.insert(studentsClass2);

        List<StudentsClassModel> retrievedClasses = repository.getAllByTeacherId(teacherId);

        assertEquals(2, retrievedClasses.size());
        assertTrue(retrievedClasses.contains(studentsClass1));
        assertTrue(retrievedClasses.contains(studentsClass2));
    }

    @Test
    void getByName() throws ClassNotFoundException {
        String className = "Class D";
        StudentsClassModel studentsClass = new StudentsClassModel(4, 4, className);
        repository.insert(studentsClass);

        List<StudentsClassModel> retrievedClasses = repository.getByName(className);

        assertEquals(1, retrievedClasses.size());
        assertEquals(studentsClass, retrievedClasses.get(0));
    }

    @Test
    void insert() throws ClassNotFoundException {
        StudentsClassModel studentsClass = new StudentsClassModel(5, 5, "Class E");
        assertTrue(repository.insert(studentsClass));

        assertNotNull(studentsClass.getId());
        StudentsClassModel retrievedStudentsClass = repository.getById(studentsClass.getId());

        assertEquals(studentsClass.getId(), retrievedStudentsClass.getId());
        assertEquals(studentsClass.getTeacherId(), retrievedStudentsClass.getTeacherId());
        assertEquals(studentsClass.getName(), retrievedStudentsClass.getName());
    }

    @Test
    void update() throws ClassNotFoundException {
        StudentsClassModel studentsClass = new StudentsClassModel(6, 6, "Class F");
        repository.insert(studentsClass);

        StudentsClassModel updatedStudentsClass = new StudentsClassModel(studentsClass.getId(), 6, "Class G");
        assertTrue(repository.update(updatedStudentsClass));

        StudentsClassModel retrievedStudentsClass = repository.getById(studentsClass.getId());
        assertEquals(studentsClass.getId(), retrievedStudentsClass.getId());
        assertEquals(updatedStudentsClass.getName(), retrievedStudentsClass.getName());
    }

    @Test
    void delete() throws ClassNotFoundException {
        StudentsClassModel studentsClass = new StudentsClassModel(7, 7, "Class H");
        repository.insert(studentsClass);

        assertTrue(repository.delete(studentsClass));
        assertThrows(Error.class, () -> repository.getById(studentsClass.getId()));
    }
}
