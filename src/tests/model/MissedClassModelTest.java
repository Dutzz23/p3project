package tests.model;

import model.MissedClassModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissedClassModelTest {

    @Test
    void getId() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        int id = missedClassModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getStudentId() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        int studentId = missedClassModel.getStudentId();
        assertEquals(2, studentId);
    }

    @Test
    void getSubjectId() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        int subjectId = missedClassModel.getSubjectId();
        assertEquals(3, subjectId);
    }

    @Test
    void getTeacherId() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        int teacherId = missedClassModel.getTeacherId();
        assertEquals(4, teacherId);
    }

    @Test
    void getGivenDate() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        java.sql.Date givenDate = missedClassModel.getGivenDate();
        assertEquals(new java.sql.Date(new Date().getTime()), givenDate);
    }

    @Test
    void getDetails() {
        MissedClassModel missedClassModel = new MissedClassModel(1, 2, 3, 4, new Date(), "Details");
        String details = missedClassModel.getDetails();
        assertEquals("Details", details);
    }
}