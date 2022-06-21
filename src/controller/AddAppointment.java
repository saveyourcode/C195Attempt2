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
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

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
    private TextField addAppointmentDateText;

    @FXML
    void onActionAddAppointmentCancel(ActionEvent event) {

        try {

            String title = addAppointmentTitleText.getText();
            String description = addAppointmentDescriptionText.getText();
            String location = addAppointmentLocationText.getText();
            String type = addAppointmentTypeText.getText();
            LocalDate date = LocalDate.parse(addAppointmentDateText.getText());
            LocalTime startTime = LocalTime.parse(addAppointmentStartingTimeText.getText());
            LocalTime endTime = LocalTime.parse(addAppointmentEndingTimeText.getText());
            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = LocalDateTime.of(date, endTime);
            int customerId = addAppointmentCustomerIDCombo.getValue().getCustomerId();
            int userId = addAppointmentUserIDCombo.getValue().getUserId();
            int contactId = addAppointmentContactsCombo.getValue().getContactId();

            AppointmentQuery.insertAppointment(title, description, location, type, start, end, customerId, userId, contactId);

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

    @FXML
    void onActionAddAppointmentSave(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAppointmentContactsCombo.setItems(ContactQuery.getAllContacts());

        addAppointmentCustomerIDCombo.setItems(CustomerQuery.getAllCustomers());

        addAppointmentUserIDCombo.setItems(UserQuery.getAllUsers());

    }
}
