package model;

import model.lib.UserRole;

public class UserModel {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final UserRole role;

    public UserModel(int id, String firstName, String lastName, UserRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserModel() {
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.role = UserRole.UNKNOWN;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRole getRole() {
        return role;
    }
}
