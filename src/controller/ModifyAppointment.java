package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable {

    private Stage stage;
    private Parent scene;

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
    void onActionModifyAppointmentCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyAppointmentSave(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
