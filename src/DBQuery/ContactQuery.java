package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class that holds the methods that query the contacts table in the database.*/
public abstract class ContactQuery {

    /** Returns an observable list containing objects that hold all the information for the contacts table.
     *
     * @return an observable list of contact objects
     */
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

    /** Returns the Contact id that matches the contact name that is passed as a parameter.
     *
     * @param contactName String contactName
     * @return the number of row affected
     */
    public static int getContactId(String contactName) {

        try {

            String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, contactName);
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
