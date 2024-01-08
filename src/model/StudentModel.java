package model;

public class StudentModel {
    private final int id;
    private final int studentsClassId;

    public StudentModel(int id, int studentsClassId) {
        this.id = id;
        this.studentsClassId = studentsClassId;
    }
    public StudentModel() {
        this.id = 0;
        this.studentsClassId = 0;
    }

    public int getId() {
        return id;
    }

    public int getStudentsClassId() {
        return studentsClassId;
    }
}
