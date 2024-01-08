package tests;

import model.GradeModel;
import org.junit.jupiter.api.Test;
import repository.GradeRepository;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeIntegrationTest {

    @Test
    void testGradeIntegration() throws ClassNotFoundException {
        GradeRepository gradeRepository = new GradeRepository();

        // Test Create (Insert)
        GradeModel newGrade = new GradeModel(0, 1, 1, 1, 9, null);
        assertTrue(gradeRepository.insert(newGrade));

        // Test Read (GetById)
        GradeModel retrievedGrade = gradeRepository.getById(newGrade.getId());
        assertNotNull(retrievedGrade);
        assertEquals(newGrade.getId(), retrievedGrade.getId());


        // Test Read (GetAllByStudentIdAndSubjectId)
        List<GradeModel> grades = gradeRepository.getAllByStudentIdAndSubjectId(1, 1);
        assertNotNull(grades);
        assertFalse(grades.isEmpty());

        // Test Read (GetAllByStudentIdAndTeacherId)
        grades = gradeRepository.getAllByStudentIdAndTeacherId(1, 1);
        assertNotNull(grades);
        assertFalse(grades.isEmpty());

        // Test Delete
        assertTrue(gradeRepository.delete(retrievedGrade));
    }
}
