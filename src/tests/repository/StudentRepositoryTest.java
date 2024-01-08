package tests.repository;

import model.StudentModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private static StudentRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new StudentRepository();
    }

    @Test
    void getById() throws ClassNotFoundException {
        StudentModel student = new StudentModel(1, 1);
        repository.insert(student);

        StudentModel retrievedStudent = repository.getById(student.getId());

        assertEquals(student.getId(), retrievedStudent.getId());
        assertEquals(student.getStudentsClassId(), retrievedStudent.getStudentsClassId());
    }

    @Test
    void getAllByStudentsClassId() throws ClassNotFoundException {
        int studentsClassId = 1;
        StudentModel student1 = new StudentModel(1, studentsClassId);
        StudentModel student2 = new StudentModel(2, studentsClassId);
        repository.insert(student1);
        repository.insert(student2);

        List<StudentModel> students = repository.getAllByStudentsClassId(studentsClassId);

        assertEquals(2, students.size());
        assertTrue(students.stream().anyMatch(s -> s.getId() == student1.getId()));
        assertTrue(students.stream().anyMatch(s -> s.getId() == student2.getId()));
    }

    @Test
    void insert() throws ClassNotFoundException {
        StudentModel student = new StudentModel(1, 1);
        assertTrue(repository.insert(student));
        assertNotNull(student.getId()); // Asigură-te că ID-ul este setat după inserare
        StudentModel retrievedStudent = repository.getById(student.getId());
        assertEquals(student.getId(), retrievedStudent.getId());
        assertEquals(student.getStudentsClassId(), retrievedStudent.getStudentsClassId());
    }

    @Test
    void update() throws ClassNotFoundException {
        StudentModel student = new StudentModel(1, 1);
        repository.insert(student);

        StudentModel updatedStudent = new StudentModel(student.getId(), 2); // Creează un student actualizat
        assertTrue(repository.update(updatedStudent));

        StudentModel retrievedStudent = repository.getById(student.getId());
        assertEquals(student.getId(), retrievedStudent.getId());
        assertEquals(updatedStudent.getStudentsClassId(), retrievedStudent.getStudentsClassId());
    }

    @Test
    void delete() throws ClassNotFoundException {
        StudentModel student = new StudentModel(1, 1);
        repository.insert(student);

        assertTrue(repository.delete(student));
        assertThrows(Error.class, () -> repository.getById(student.getId()));
    }
}
