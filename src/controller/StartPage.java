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

/** CLass that holds all of the methods that control the start page view.*/
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

    /** When selected the appointments table view is populated with all the available appointments.*/
    @FXML
    void onActionStartPageAllRadioButton(ActionEvent event) {

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());

    }

    /** When selected the user is taken to the add appointment page.*/
    @FXML
    void onActionStartPageAppointmentsAdd(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Asks the user if they would like to delete the selected appointment and if they confirm then the selected
     * appointment is deleted and a message is displayed with the deleted appointments ID and type.*/
    @FXML
    void onActionStartPageAppointmentsDelete(ActionEvent event) {

        if (startPageAppointmentsTableView.getSelectionModel().getSelectedItem() != null) {

            Appointment appointment = startPageAppointmentsTableView.getSelectionModel().getSelectedItem();

            if (AlertMessages.confirmationAlert("Are you sure you want to delete the appointment with " +
                    "appointment ID " + appointment.getAppointmentId() + " and type " + appointment.getType() + " ?")) {

                AppointmentQuery.deleteAppointment(appointment.getAppointmentId());

                startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments());

                AlertMessages.informationAlert("The appointment with appointment id " + appointment.getAppointmentId() +
                        " and type " + appointment.getType() + " has been deleted.");

            }

        }

    }

    /** If an appointment is selected it takes the user and the selected appointment object to the modify appointment
     * page.*/
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

    }

    /** Takes the user to the add customer page.*/
    @FXML
    void onActionStartPageCustomerAdd(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Asks the user if they would like to delete the selected customer and if they confirm then the selected
     * customer's appointments are deleted and then the customer is deleted followed by a message that displays the
     * deleted customer's name.*/
    @FXML
    void onActionStartPageCustomerDelete(ActionEvent event) {

        if (startPageCustomersTableView.getSelectionModel().getSelectedItem() != null) {

            Customer customer = startPageCustomersTableView.getSelectionModel().getSelectedItem();

            if (AlertMessages.confirmationAlert("Are you Sure you want to delete the customer " +
                    customer.getCustomerName() + " and all associated appointments?")) {

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

    }

    /** If pressed when a customer is selected, the user is taken to the modify customer page where they can change
     * an existing customer's data.*/
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

    /** Ends the program.*/
    @FXML
    void onActionStartPageExit(ActionEvent event) {

        DBConnection.closeConnection();

        System.exit(0);

    }

    /** Returns the user to the login page.*/
    @FXML
    void onActionStartPageLogOut(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Displays the appointments that have a start time within the next month.*/
    @FXML
    void onActionStartPageMonthRadioButton(ActionEvent event) {

        final LocalDateTime ldt = LocalDateTime.now().plusHours(721);

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments().stream().
                filter((object) -> object.getStartTime().isBefore(ldt))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

    }

    /** When pressed, takes the user to the reports page.*/
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

    /** Displays the appointments that have a start time within the next week.*/
    @FXML
    void onActionStartPageWeekRadioButton(ActionEvent event) {

        final LocalDateTime ldt = LocalDateTime.now().plusHours(169);

        startPageAppointmentsTableView.setItems(AppointmentQuery.getAllAppointments().stream().
                filter((object) -> object.getStartTime().isBefore(ldt))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

    }

    /** Upon the start page being loaded, the customer and appointment table views are set up and populated with all
     * of the customers and appointments respectively.*/
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

    /** When a user first logs in a message is displayed telling them if any meetings are starting within the next
     * fifteen minutes.*/
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
