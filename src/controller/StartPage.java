package controller;

import DBQuery.AppointmentQuery;
import DBQuery.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class StartPage implements Initializable {

    private Parent scene;
    private Stage stage;

    @FXML
    private TableView<Appointment> startPageAppointmentsTableView;

    @FXML
    private TableColumn<Appointment, Integer> startPageAppointmentsTableViewAppointmentsCol;

    @FXML
    private TableColumn<Appointment, Integer> startPageAppointmentsTableViewContactCol;

    @FXML
    private TableColumn<Appointment, Integer> startPageAppointmentsTableViewCustomerIDCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startPageAppointmentsTableViewDescriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startPageAppointmentsTableViewEndingCol;

    @FXML
    private TableColumn<Appointment, String> startPageAppointmentsTableViewLocationCol;

    @FXML
    private TableColumn<Appointment, String> startPageAppointmentsTableViewStartingCol;

    @FXML
    private TableColumn<Appointment, String> startPageAppointmentsTableViewTitleCol;

    @FXML
    private TableColumn<Appointment, String> startPageAppointmentsTableViewTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> startPageAppointmentsTableViewUserIDCol;

    @FXML
    private TableView<Customer> startPageCustomersTableView;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewAddressCol;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewCountryCol;

    @FXML
    private TableColumn<Customer, Integer> startPageCustomersTableViewCustomerIDCol;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewCustomerNameCol;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewDivisionCol;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewPhoneNumberCol;

    @FXML
    private TableColumn<Customer, String> startPageCustomersTableViewPostalCodeCol;

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

        startPageCustomersTableView.setItems(CustomerQuery.getAllCustomers());

        startPageCustomersTableViewCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        startPageCustomersTableViewCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        startPageCustomersTableViewAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        startPageCustomersTableViewPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        startPageCustomersTableViewPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        startPageCustomersTableViewDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        startPageCustomersTableViewCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());

        startPageAppointmentsTableViewAppointmentsCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        startPageAppointmentsTableViewTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        startPageAppointmentsTableViewDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startPageAppointmentsTableViewLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        startPageAppointmentsTableViewContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        startPageAppointmentsTableViewTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startPageAppointmentsTableViewStartingCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        startPageAppointmentsTableViewEndingCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        startPageAppointmentsTableViewCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        startPageAppointmentsTableViewUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userId"));


    }
}
