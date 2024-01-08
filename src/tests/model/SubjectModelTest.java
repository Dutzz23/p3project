package tests.model;

import model.SubjectModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectModelTest {

    @Test
    void getId() {
        SubjectModel subjectModel = new SubjectModel(1, "Math");
        int id = subjectModel.getId();
        assertEquals(1, id);
    }

    @Test
    void getName() {
        SubjectModel subjectModel = new SubjectModel(1, "Math");
        String name = subjectModel.getName();
        assertEquals("Math", name);
    }
}
