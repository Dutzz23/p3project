package model;

public class TeacherModel {
    private final int id;
    private final int userId;
    private final int subjectId;

    public TeacherModel(int id, int userId, int subjectId) {
        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public TeacherModel() {
        this.id = 0;
        this.userId = 0;
        this.subjectId = 0;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getSubjectId() {
        return subjectId;
    }
}
