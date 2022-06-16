package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModifyAppointment {

    @FXML
    private TextField modifyAppointmentAppointmentIDText;

    @FXML
    private ComboBox<?> modifyAppointmentContactsCombo;

    @FXML
    private ComboBox<?> modifyAppointmentCustomerIDCombo;

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
    private ComboBox<?> modifyAppointmentUserIDCombo;

    @FXML
    void onActionModifyAppointmentCancel(ActionEvent event) {

    }

    @FXML
    void onActionModifyAppointmentSave(ActionEvent event) {

    }
}
