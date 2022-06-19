package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {

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
}
