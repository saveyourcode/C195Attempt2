package controller;

import DBQuery.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Reports implements Initializable {

    private Parent scene;
    private Stage stage;

    @FXML
    private Label reportsFebruaryAmountLabel;

    @FXML
    private Label reportsJuneAmountLabel;

    @FXML
    private Label reportsAprilAmountLabel;

    @FXML
    private Label reportsAugustAmountLabel;

    @FXML
    private Label reportsDecemberAmountLabel;

    @FXML
    private Label reportsJanuaryAmountLabel;

    @FXML
    private Label reportsJulyAmountLabel;

    @FXML
    private Label reportsMarchAmountLabel;

    @FXML
    private Label reportsMayAmountLabel;

    @FXML
    private Label reportsNovemberAmountLabel;

    @FXML
    private Label reportsOctoberAmountLabel;

    @FXML
    private Label reportsSeptemberAmountLabel;

    @FXML
    private ComboBox<String> totalAppointmentTabTypeCombo;

    @FXML
    private ComboBox<String> scheduleByContactCombo;

    @FXML
    void onActionTypeSelected(ActionEvent event) {

        String type = totalAppointmentTabTypeCombo.getValue();
        reportsJanuaryAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 1)));
        reportsFebruaryAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 2)));
        reportsMarchAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 3)));
        reportsAprilAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 4)));
        reportsMayAmountLabel.setText(String.valueOf((returnCountForTypeAndMonth(type, 5))));
        reportsJuneAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 6)));
        reportsJulyAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type,7)));
        reportsAugustAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 8)));
        reportsSeptemberAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 9)));
        reportsOctoberAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 10)));
        reportsNovemberAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 11)));
        reportsDecemberAmountLabel.setText(String.valueOf(returnCountForTypeAndMonth(type, 12)));

    }

    @FXML
    void onActionContactSelected(ActionEvent event) {

    }


    @FXML
    void onActionReturnToStartPage(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<String> typeList = AppointmentQuery.getAllAppointments().stream()
                .map((appt) -> appt.getType())
                .distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if (!typeList.isEmpty()) {
            totalAppointmentTabTypeCombo.setItems(typeList);
        }

        ObservableList<String> contactList = AppointmentQuery.getAllAppointments().stream()
                .map((appt) -> appt.getContact())
                .distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if(!contactList.isEmpty()) {
            scheduleByContactCombo.setItems(contactList);
        }

    }

    public long returnCountForTypeAndMonth(String type, int month) {

        long count = AppointmentQuery.getAllAppointments().stream()
                .filter((appt) -> appt.getType().equals(type))
                .filter((appt) -> appt.getStartTime().getMonthValue() == month)
                .count();

        return count;
    }
}
