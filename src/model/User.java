package model;

/** Class that holds the methods and data for the User class.*/
public class User {

    private int userId;
    private String username;
    private String password;

    /** The User class constructor.*/
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /** Returns the user id.*/
    public int getUserId() {
        return userId;
    }

    /** Sets the value of the user id variable.*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Returns the username.*/
    public String getUsername() {
        return username;
    }

    /** Sets the value of the username variable.*/
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the password.*/
    public String getPassword() {
        return password;
    }

    /** Sets the value of the password variable.*/
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.username;
    }
}
