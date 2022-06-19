package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AppointmentQuery {

    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> resultsList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                LocalDateTime start = null;
                LocalDateTime end = null;
                int customerId = results.getInt("Customer_ID");
                int userId = results.getInt("User_ID");
                int contactId = results.getInt("Contact_ID");

                resultsList.add(new Appointment(appointmentId, title, description, location, type, start, end,
                        customerId, userId, contactId));

            }

        } catch(SQLException e) {

        }

        return resultsList;

    }
}
