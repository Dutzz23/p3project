package model;

import java.util.Date;

public class MissedClassModel {
    private final int id;
    private final int studentId;
    private final int subjectId;
    private final int teacherId;
    private final Date givenDate;
    private final String details;

    public MissedClassModel(int id, int usedId, int subjectId, int teacherId, Date date, String details) {
        this.id = id;
        this.studentId = usedId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.givenDate = date;
        this.details = details;
    }

    public MissedClassModel() {
        this.id = 0;
        this.studentId = 0;
        this.subjectId = 0;
        this.teacherId = 0;
        this.givenDate = null;
        this.details = null;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public java.sql.Date getGivenDate() {
        return (java.sql.Date) givenDate;
    }

    public String getDetails() {
        return details;
    }
}
