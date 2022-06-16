package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddAppointment {

    @FXML
    private TextField addAppointmentAppointmentIDText;

    @FXML
    private ComboBox<?> addAppointmentContactsCombo;

    @FXML
    private ComboBox<?> addAppointmentCustomerIDCombo;

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
    private ComboBox<?> addAppointmentUserIDCombo;

    @FXML
    void onActionAddAppointmentCancel(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentSave(ActionEvent event) {

    }

}
