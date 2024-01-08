package model.lib;

public enum UserRole {
    UNKNOWN(0),

    STUDENT(1),

    TEACHER(2),

    HEADMASTER(3),

    ADMIN(4);

    public final int value;

    private UserRole(int value) {
        this.value = value;
    }
}
