package model;

public class StudentsClassModel {
    private final int id;
    private final int teacherId;
    private final String name;

    public StudentsClassModel(int id, int teacherId, String name) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
    }
    public StudentsClassModel() {
        this.id = 0;
        this.teacherId = 0;
        this.name = null;
    }

    public int getId() {
        return id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }
}
