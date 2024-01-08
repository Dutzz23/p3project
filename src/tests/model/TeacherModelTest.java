package tests.model;

import model.TeacherModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherModelTest {

    @Test
    void getId() {
        TeacherModel teacherModel = new TeacherModel(1, 2, 3);
        int id = teacherModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getUserId() {
        TeacherModel teacherModel = new TeacherModel(1, 2, 3);
        int userId = teacherModel.getUserId();
        assertEquals(2, userId);
    }

    @Test
    void getSubjectId() {
        TeacherModel teacherModel = new TeacherModel(1, 2, 3);
        int subjectId = teacherModel.getSubjectId();
        assertEquals(3, subjectId);
    }
}
