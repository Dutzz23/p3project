package tests.model;

import model.GradeModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GradeModelTest {

    @Test
    void getId() {
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, new Date());
        int id = gradeModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getStudentId() {
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, new Date());
        int studentId = gradeModel.getStudentId();
        assertEquals(2, studentId);
    }

    @Test
    void getSubjectId() {
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, new Date());
        int subjectId = gradeModel.getSubjectId();
        assertEquals(3, subjectId);
    }

    @Test
    void getTeacherId() {
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, new Date());
        int teacherId = gradeModel.getTeacherId();
        assertEquals(4, teacherId);
    }

    @Test
    void getValue() {
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, new Date());
        int value = gradeModel.getValue();
        assertEquals(5, value);
    }

    @Test
    void getGradeGivenData() {
        Date givenDate = new Date();
        GradeModel gradeModel = new GradeModel(1, 2, 3, 4, 5, givenDate);
        java.sql.Date gradeGivenData = gradeModel.getGradeGivenData();
        assertEquals(new java.sql.Date(givenDate.getTime()), gradeGivenData);
    }
}
