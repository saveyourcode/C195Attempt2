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
import java.util.ResourceBundle;

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
    void onActionModifyAppointmentCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyAppointmentSave(ActionEvent event) throws IOException {

        try {

            String title = modifyAppointmentTitleText.getText();
            String description = modifyAppointmentDescriptionText.getText();
            String location = modifyAppointmentLocationText.getText();
            String type = modifyAppointmentTypeText.getText();
            LocalDate date = LocalDate.parse(modifyAppointmentDateText.getText());
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

        } catch(Exception e) {
            e.printStackTrace();
        }

//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferAppointment(Appointment appointment) {

        modifyAppointmentTitleText.setText(appointment.getTitle());
        modifyAppointmentDescriptionText.setText(appointment.getDescription());
        modifyAppointmentLocationText.setText(appointment.getLocation());
        modifyAppointmentTypeText.setText(appointment.getType());
        modifyAppointmentDateText.setText(String.valueOf(LocalDate.from(appointment.getStartTime())));
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
