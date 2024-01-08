package tests.model;

import model.StudentModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentModelTest {

    @Test
    void getId() {
        StudentModel studentModel = new StudentModel(1, 2);
        int id = studentModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getStudentsClassId() {
        StudentModel studentModel = new StudentModel(1, 2);
        int studentsClassId = studentModel.getStudentsClassId();
        assertEquals(2, studentsClassId);
    }
}