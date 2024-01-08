package model;

import java.util.Date;

public class GradeModel {
    private final int id;
    private final int studentId;
    private final int subjectId;
    private final int teacherId;
    private final int value;
    private final Date gradeGivenData;
    //TODO might add studentsClassId (to GradeRepo as well)

    public GradeModel(int id, int studentId, int subjectId, int teacherId, int value, Date gradeGivenData) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.value = value;
        this.gradeGivenData = gradeGivenData;
    }

    public GradeModel() {
        this.id = 0;
        this.studentId = 0;
        this.subjectId = 0;
        this.teacherId = 0;
        this.value = -1;
        this.gradeGivenData = null;
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

    public int getValue() {
        return value;
    }

    public java.sql.Date getGradeGivenData() {
        return (java.sql.Date) gradeGivenData;
    }
}
