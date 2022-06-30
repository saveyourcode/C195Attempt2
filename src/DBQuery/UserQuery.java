package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class that holds the methods that query the users table in the database.*/
public abstract class UserQuery {

    /** Returns an observable list containing objects that hold all the information for the users table.*/
    public static ObservableList<User> getAllUsers() {

        ObservableList<User> returnedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int userId = results.getInt("User_ID");
                String username = results.getString("User_Name");
                String password = results.getString("Password");
                returnedList.add(new User(userId, username, password));
            }

        } catch (SQLException e) {

        }

        return returnedList;

    }

    /** Returns the password that matches the username that is passed as a parameter.*/
    public static String getPassword(String username) {

        try {

            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                return results.getString("Password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "empty";

    }
}
