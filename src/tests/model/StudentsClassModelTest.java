package tests.model;

import model.StudentsClassModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentsClassModelTest {

    @Test
    void getId() {
        StudentsClassModel studentsClassModel = new StudentsClassModel(1, 2, "ClassA");
        int id = studentsClassModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getTeacherId() {
        StudentsClassModel studentsClassModel = new StudentsClassModel(1, 2, "ClassA");
        int teacherId = studentsClassModel.getTeacherId();
        assertEquals(2, teacherId);
    }

    @Test
    void getName() {
        StudentsClassModel studentsClassModel = new StudentsClassModel(1, 2, "ClassA");
        String name = studentsClassModel.getName();
        assertEquals("ClassA", name);
    }
}