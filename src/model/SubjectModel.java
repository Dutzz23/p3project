package model;

public class SubjectModel {
    private final int id;
    private final String name;

    public SubjectModel(int id, String subjectName) {
        this.id = id;
        this.name = subjectName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
