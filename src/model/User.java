package model;

/** Class that holds the methods and data for the User class.*/
public class User {

    private int userId;
    private String username;
    private String password;

    /** The User class constructor.
     *
     * @param userId int userId
     * @param username String username
     * @param password String password
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /** Returns the user id.
     *
     * @return int userId
     */
    public int getUserId() {
        return userId;
    }

    /** Sets the value of the user id variable.
     *
     * @param userId int userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Returns the username.
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /** Sets the value of the username variable.
     *
     * @param username String username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the password.
     *
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /** Sets the value of the password variable.
     *
     * @param password String password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.username;
    }
}
