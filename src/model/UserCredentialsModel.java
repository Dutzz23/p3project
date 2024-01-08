package model;

public class UserCredentialsModel {
    private final int id;
    private final int userId;
    private final String password;
    private final String email;

    public UserCredentialsModel(int id, int userId, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public UserCredentialsModel() {
        this.id = 0;
        this.userId = 0;
        this.password = null;
        this.email = null;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
