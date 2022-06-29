package controller;

import DBQuery.AppointmentQuery;
import DBQuery.ContactQuery;
import DBQuery.CustomerQuery;
import DBQuery.UserQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import utility.AlertMessages;
import utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/** Class that holds all of the methods that control the add appointment view.*/
public class AddAppointment implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private TextField addAppointmentAppointmentIDText;

    @FXML
    private ComboBox<Contact> addAppointmentContactsCombo;

    @FXML
    private ComboBox<Customer> addAppointmentCustomerIDCombo;

    @FXML
    private TextField addAppointmentDescriptionText;

    @FXML
    private TextField addAppointmentEndingTimeText;

    @FXML
    private TextField addAppointmentLocationText;

    @FXML
    private TextField addAppointmentStartingTimeText;

    @FXML
    private TextField addAppointmentTitleText;

    @FXML
    private TextField addAppointmentTypeText;

    @FXML
    private ComboBox<User> addAppointmentUserIDCombo;


    @FXML
    private DatePicker addAppointmentDatePicker;

    /** Returns the user to the Startpage when pressed.*/
    @FXML
    void onActionAddAppointmentCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Collects all of the data inputted by the user and creates a Appointment object that is checked for appointment
     * time overlaps and if the appointment is within business hours before adding the appointment to the database.*/
    @FXML
    void onActionAddAppointmentSave(ActionEvent event) throws IOException {

        try {

            String title = addAppointmentTitleText.getText();
            String description = addAppointmentDescriptionText.getText();
            String location = addAppointmentLocationText.getText();
            String type = addAppointmentTypeText.getText();
            LocalDate date = addAppointmentDatePicker.getValue();
            LocalTime startTime = LocalTime.parse(addAppointmentStartingTimeText.getText());
            LocalTime endTime = LocalTime.parse(addAppointmentEndingTimeText.getText());
            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = LocalDateTime.of(date, endTime);
            int customerId = addAppointmentCustomerIDCombo.getValue().getCustomerId();
            int userId = addAppointmentUserIDCombo.getValue().getUserId();
            int contactId = addAppointmentContactsCombo.getValue().getContactId();

            if (!(Helper.checkIfWithinBusinessHours(start)) || !(Helper.checkIfWithinBusinessHours(end))) {

                AlertMessages.warningAlert("Meetings can't be scheduled outside of 08:00 - 10:00 EST");
                return;
            }

            Appointment newAppointment = new Appointment(-1, title, description, location, addAppointmentContactsCombo.getValue().getContactName(),
                    type, start, end, customerId, userId);
            if (Appointment.checkAppointmentConflict(newAppointment)) {

                AlertMessages.warningAlert("The meeting can't be scheduled due to a conflict with an existing meeting.");
                return;
            }

            AppointmentQuery.insertAppointment(title, description, location, type, start, end, customerId, userId, contactId);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch(DateTimeParseException e) {

            String header = "Time needs to be in HH:MM format.";
            String message = "Exception: " + e + "\n" + "\n" + "Exception: " + e.getMessage();
            AlertMessages.errorAlert(header, message);

        } catch(NullPointerException e) {

            String header = "Need to make a selection for the Customer, Contact, and User fields.";
            String message = "Exception: " + e + "\n" + "\n" +"Exception: " + e.getMessage();
            AlertMessages.errorAlert(header, message);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /** When the add appointment view is loaded the contacts, customer, and user combo boxes are populated with data
     * and the current date is set as the datepicker's value.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAppointmentContactsCombo.setItems(ContactQuery.getAllContacts());

        addAppointmentCustomerIDCombo.setItems(CustomerQuery.getAllCustomers());

        addAppointmentUserIDCombo.setItems(UserQuery.getAllUsers());

        addAppointmentDatePicker.setValue(LocalDate.now());

    }
}
