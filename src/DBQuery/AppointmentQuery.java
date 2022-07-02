package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Class that holds the methods that query the appointments table in the database.*/
public abstract class AppointmentQuery {

    /** Returns an observable list containing objects that hold all the information for the appointments table.
     *
     * @return an observable list of appointment objects
     */
    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> resultsList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description,\n" +
                    " appointments.Location, appointments.Type, appointments.Start, appointments.End,\n" +
                    " appointments.Customer_ID, appointments.Contact_ID, appointments.User_ID, contacts.Contact_Name\n" +
                    " FROM appointments INNER JOIN contacts WHERE appointments.Contact_ID = \n" +
                    "contacts.Contact_ID;";

            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String contact = results.getString("Contact_Name");
                String type = results.getString("Type");
                LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = results.getTimestamp("End").toLocalDateTime();
                int customerId = results.getInt("Customer_ID");
                int userId = results.getInt("User_ID");


                resultsList.add(new Appointment(appointmentId, title, description, location, contact, type, start, end,
                        customerId, userId));

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return resultsList;

    }

    /** Inserts a new appointment entry into the appointments table.
     *
     * @param title String title
     * @param description String description
     * @param location String Location
     * @param type String type
     * @param start LocalDateTime start
     * @param end LocalDateTime end
     * @param customerId int customerId
     * @param userId int userId
     * @param contactId int contactId
     * @return the number of row affected
     */
    public static int insertAppointment(String title, String description, String location, String type,
                                        LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        try {

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                    " Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, location);
            statement.setString(4, type);
            statement.setTimestamp(5, Timestamp.valueOf(start));
            statement.setTimestamp(6, Timestamp.valueOf(end));
            statement.setInt(7, customerId);
            statement.setInt(8, userId);
            statement.setInt(9, contactId);

            return statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /** Updates the information of an existing entry in the appointments table.
     *
     * @param title String title
     * @param description String description
     * @param location String location
     * @param type String type
     * @param start LocalDateTime start
     * @param end LocalDateTime end
     * @param customerId int customerId
     * @param userId int userId
     * @param contactId int contactId
     * @param appointmentId int appointmentId
     * @return the number of items in the database that were affected
     */
    public static int updateAppointment(String title, String description, String location, String type,
                                        LocalDateTime start, LocalDateTime end, int customerId, int userId,
                                        int contactId, int appointmentId) {

        try {

            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?," +
                    "End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, location);
            statement.setString(4, type);
            statement.setTimestamp(5, Timestamp.valueOf(start));
            statement.setTimestamp(6, Timestamp.valueOf(end));
            statement.setInt(7, customerId);
            statement.setInt(8, userId);
            statement.setInt(9, contactId);
            statement.setInt(10, appointmentId);
            return statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /** Deletes an appointment with the matching appointment id from the appointments table.
     *
     * @param appointmentId the id of the appointment that is deleted
     * @return the number of rows affected
     */
    public static int deleteAppointment(int appointmentId) {

        try {

            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, appointmentId);
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
