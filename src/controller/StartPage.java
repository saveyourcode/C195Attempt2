package controller;

import DBConnect.DBConnection;
import DBQuery.AppointmentQuery;
import DBQuery.CustomerQuery;
import javafx.collections.FXCollections;
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
import utility.AlertMessages;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());

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

        if (startPageAppointmentsTableView.getSelectionModel().getSelectedItem() != null) {

            Appointment appointment = startPageAppointmentsTableView.getSelectionModel().getSelectedItem();

            AppointmentQuery.deleteAppointment(appointment.getAppointmentId());

            startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());
        }

    }

    @FXML
    void onActionStartPageAppointmentsModify(ActionEvent event) throws IOException {

        if (startPageAppointmentsTableView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();

            ModifyAppointment modifyAppointmentController = loader.getController();
            modifyAppointmentController.transferAppointment(startPageAppointmentsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();

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

        if (startPageCustomersTableView.getSelectionModel().getSelectedItem() != null) {

            Customer customer = startPageCustomersTableView.getSelectionModel().getSelectedItem();

            AppointmentQuery.getAllAppointments().stream()
                    .filter((object) -> object.getCustomerId() == customer.getCustomerId())
                    .forEach((object) -> AppointmentQuery.deleteAppointment(object.getAppointmentId()));

            CustomerQuery.deleteCustomer(customer.getCustomerId());

            startPageCustomersTableView.setItems(CustomerQuery.getAllCustomers());

            startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());

            AlertMessages.informationAlert("The customer records and all related appointments for "
                    + customer.getCustomerName() + " have been deleted.");

        }

    }

    @FXML
    void onActionStartPageCustomerModify(ActionEvent event) throws IOException {

        if (startPageCustomersTableView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();

            ModifyCustomer modifyCustomerController = loader.getController();
            modifyCustomerController.transferCustomer(startPageCustomersTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

//            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            scene = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
//            stage.setScene(new Scene(scene));
//            stage.show();

        }

    }

    @FXML
    void onActionStartPageExit(ActionEvent event) {

        DBConnection.closeConnection();

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

        final LocalDateTime ldt = LocalDateTime.now().plusHours(721);

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments().stream().
                filter((object) -> object.getStartTime().isBefore(ldt))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

    }

    @FXML
    void onActionStartPageReports(ActionEvent actionEvent) {

        try {

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }

    }

    @FXML
    void onActionStartPageWeekRadioButton(ActionEvent event) {

        final LocalDateTime ldt = LocalDateTime.now().plusHours(169);

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments().stream().
                filter((object) -> object.getStartTime().isBefore(ldt))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

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

    public void transferFromLogin() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in15min = LocalDateTime.now().plusMinutes(15);

        ArrayList<Appointment> appointmentList = AppointmentQuery.getAllAppointments().stream()
                .filter((appt) -> (appt.getStartTime().isAfter(now) && appt.getStartTime().isBefore(in15min)))
                .collect(Collectors.toCollection(ArrayList::new));

        if (appointmentList.isEmpty()) {

            AlertMessages.informationAlert("There are no upcoming appointments");

        } else {

            String message = "";
            for(Appointment appt: appointmentList) {

                message += ("Appointment ID: " + appt.getAppointmentId() + " is at " + appt.getStartTime().toLocalTime() + "\n");

            }

            AlertMessages.informationAlert("Upcoming Appointments", message);
        }

    }
}
