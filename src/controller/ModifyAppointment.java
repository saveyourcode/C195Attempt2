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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/** CLass that holds all of the methods that control the modify appointment view.*/
public class ModifyAppointment implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private TextField modifyAppointmentAppointmentIDText;

    @FXML
    private ComboBox<Contact> modifyAppointmentContactsCombo;

    @FXML
    private ComboBox<Customer> modifyAppointmentCustomerIDCombo;

    @FXML
    private TextField modifyAppointmentDescriptionText;

    @FXML
    private TextField modifyAppointmentEndingTimeText;

    @FXML
    private TextField modifyAppointmentLocationText;

    @FXML
    private TextField modifyAppointmentStartingTimeText;

    @FXML
    private TextField modifyAppointmentTitleText;

    @FXML
    private TextField modifyAppointmentTypeText;

    @FXML
    private ComboBox<User> modifyAppointmentUserIDCombo;

    @FXML
    private TextField modifyAppointmentDateText;

    @FXML
    private DatePicker modifyAppointmentDatePicker;

    /** Returns the user to the Startpage when pressed.
     *
     * @param event the cancel button is pressed
     * @throws IOException if the startpage view file fails to load
     */
    @FXML
    void onActionModifyAppointmentCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Creates an appointment object that has its time checked to see if it's within business hours and doesn't overlap
     * with an existing meeting before updating an appointment in the database.
     *
     * @param event the save button is pressed
     * @throws IOException if the startpage view fails to load
     */
    @FXML
    void onActionModifyAppointmentSave(ActionEvent event) throws IOException {

        try {

            String title = modifyAppointmentTitleText.getText();
            String description = modifyAppointmentDescriptionText.getText();
            String location = modifyAppointmentLocationText.getText();
            String type = modifyAppointmentTypeText.getText();
            LocalDate date = modifyAppointmentDatePicker.getValue();
            LocalTime startTime = LocalTime.parse(modifyAppointmentStartingTimeText.getText());
            LocalTime endTime = LocalTime.parse(modifyAppointmentEndingTimeText.getText());
            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = LocalDateTime.of(date, endTime);
            int customerId = modifyAppointmentCustomerIDCombo.getValue().getCustomerId();
            int userId = modifyAppointmentUserIDCombo.getValue().getUserId();
            int contactId = modifyAppointmentContactsCombo.getValue().getContactId();
            int appointmentId = Integer.parseInt(modifyAppointmentAppointmentIDText.getText());

            if (!(Helper.checkIfWithinBusinessHours(start)) || !(Helper.checkIfWithinBusinessHours(end))) {

                AlertMessages.warningAlert("Meetings can't be scheduled outside of 08:00 - 10:00 EST");
                return;
            }

            Appointment newAppointment = new Appointment(appointmentId, title, description, location, modifyAppointmentContactsCombo.getValue().getContactName(),
                    type, start, end, customerId, userId);

            if (Appointment.checkAppointmentConflict(newAppointment)) {

                AlertMessages.warningAlert("The meeting can't be scheduled due to a conflict with an existing meeting.");
                return;

            }

            AppointmentQuery.updateAppointment(title, description, location, type, start, end, customerId, userId,
                    contactId, appointmentId);


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** The appointment that is chosen to be modified is sent to the modify appointment view from the start page and
     * the appointment's data is used to populate the combo boxes and text fields.
     *
     * @param appointment the appointment object chosen for the appointment tableview on the startpage
     */
    public void transferAppointment(Appointment appointment) {

        modifyAppointmentTitleText.setText(appointment.getTitle());
        modifyAppointmentDescriptionText.setText(appointment.getDescription());
        modifyAppointmentLocationText.setText(appointment.getLocation());
        modifyAppointmentTypeText.setText(appointment.getType());
        modifyAppointmentDatePicker.setValue(LocalDate.from(appointment.getStartTime()));
        modifyAppointmentStartingTimeText.setText(String.valueOf(LocalTime.from(appointment.getStartTime())));
        modifyAppointmentEndingTimeText.setText(String.valueOf(LocalTime.from(appointment.getEndTime())));
        modifyAppointmentAppointmentIDText.setText(String.valueOf(appointment.getAppointmentId()));

        modifyAppointmentCustomerIDCombo.setItems(CustomerQuery.getAllCustomers());

        Customer customer = CustomerQuery.getAllCustomers().stream()
                .filter((object) -> appointment.getCustomerId() == object.getCustomerId())
                .findFirst().orElse(null);

        modifyAppointmentCustomerIDCombo.setValue(customer);

        modifyAppointmentUserIDCombo.setItems(UserQuery.getAllUsers());

        User user = UserQuery.getAllUsers().stream()
                .filter((object) -> appointment.getUserId() == object.getUserId())
                .findFirst().orElse(null);

        modifyAppointmentUserIDCombo.setValue(user);

        modifyAppointmentContactsCombo.setItems(ContactQuery.getAllContacts());

        Contact contact = ContactQuery.getAllContacts().stream()
                .filter((object) -> ContactQuery.getContactId(appointment.getContact()) == object.getContactId())
                .findFirst().orElse(null);

        modifyAppointmentContactsCombo.setValue(contact);
    }
}
