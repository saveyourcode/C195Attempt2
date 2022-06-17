package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPage implements Initializable {

    private Parent scene;
    private Stage stage;

    @FXML
    private TableView<?> startPageAppointmentsTableView;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewAppointmentsCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewContactCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewCustomerIDCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewDescriptionCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewEndingCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewLocationCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewStartingCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewTitleCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewTypeCol;

    @FXML
    private TableColumn<?, ?> startPageAppointmentsTableViewUserIDCol;

    @FXML
    private TableView<?> startPageCustomersTableView;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewAddressCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewCountryCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewCustomerIDCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewCustomerNameCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewDivisionCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewPhoneNumberCol;

    @FXML
    private TableColumn<?, ?> startPageCustomersTableViewPostalCodeCol;

    @FXML
    void onActionStartPageAllRadioButton(ActionEvent event) {

    }

    @FXML
    void onActionStartPageAppointmentsAdd(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionStartPageAppointmentsDelete(ActionEvent event) {

    }

    @FXML
    void onActionStartPageAppointmentsModify(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionStartPageCustomerAdd(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionStartPageCustomerDelete(ActionEvent event) {



    }

    @FXML
    void onActionStartPageCustomerModify(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionStartPageExit(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    void onActionStartPageLogOut(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionStartPageMonthRadioButton(ActionEvent event) {

    }

    @FXML
    void onActionStartPageReports(ActionEvent event) {

    }

    @FXML
    void onActionStartPageWeekRadioButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
