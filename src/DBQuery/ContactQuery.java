package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactQuery {

    public static ObservableList<Contact> getAllContacts() {

        ObservableList<Contact> returnedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int contactId = results.getInt("Contact_ID");
                String contactName = results.getString("Contact_Name");
                String email = results.getString("Email");
                returnedList.add(new Contact(contactId, contactName, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnedList;

    }

    public static int getContactId(String contactName) {

        try {

            String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                return results.getInt("Contact_ID");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;


    }
}
