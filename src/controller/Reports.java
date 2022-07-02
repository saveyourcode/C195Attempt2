package controller;

import DBQuery.AppointmentQuery;
import DBQuery.CountryQuery;
import DBQuery.CustomerQuery;
import DBQuery.DivisionQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/** CLass that holds all of the methods that control the reports view.*/
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
    private TableColumn<Appointment, Integer> scheduleByContactApptIDCol;

    @FXML
    private TableColumn<Appointment, Integer> scheduleByContactCustIDCol;

    @FXML
    private TableColumn<Appointment, String> scheduleByContactDescriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> scheduleByContactEndCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> scheduleByContactStartCol;

    @FXML
    private TableView<Appointment> scheduleByContactTableView;

    @FXML
    private TableColumn<Appointment, String> scheduleByContactTitleCol;

    @FXML
    private TableColumn<Appointment, String> scheduleByContactTypeCol;

    @FXML
    private TableColumn<Customer, String> customersByDivisionAddressCol;

    @FXML
    private ComboBox<String> customersByDivisionCountryCombo;

    @FXML
    private TableColumn<Customer, Integer> customersByDivisionCustIDCol;

    @FXML
    private TableColumn<Customer, String> customersByDivisionCustNameCol;

    @FXML
    private ComboBox<String> customersByDivisionDivisionCombo;

    @FXML
    private TableColumn<Customer, String> customersByDivisionPhoneNumberCol;

    @FXML
    private TableColumn<Customer, String> customersByDivisionPostalCodeCol;

    @FXML
    private TableView<Customer> customersByDivisionTableView;

    /** The meeting total based on meeting type and month is displayed when a meeting type is chosen from the
     * combo box.
     *
     * @param event an item in the type combo box is selected
     */
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

    /** When a contact is chosen the table view is populated with all the meetings associated with the contact.
     *
     * @param event an item in the contacts combo box is selected
     */
    @FXML
    void onActionContactSelected(ActionEvent event) {

        String chosenContact = scheduleByContactCombo.getValue();

        ObservableList<Appointment> apptList = AppointmentQuery.getAllAppointments().stream()
                .filter((appt) -> appt.getContact().equals(chosenContact))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        scheduleByContactTableView.setItems(apptList);

    }


    /** Returns the user to the Startpage when pressed.
     *
     * @param event the returned to start page button is pressed
     * @throws IOException if the view file fails to load
     */
    @FXML
    void onActionReturnToStartPage(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** When a country is selected, the division combo box is populated with only the divisions that exist within that
     * country and currently are associated with a customer.
     *
     * <p>This method contains a lambda expression that returns an observable list containing a list of the unique
     * instances of countries related to all customers. It first filters all customers for those that match
     * the country selected in the combo box.  It then maps the list of objects to a list of country name strings.
     * Finally it removes repeated instances of country names so only a list of unique country names is returned.
     * This is used to fill the country combo box with only countries that are associated with an existing customer
     * as opposed to filling it with every country option.</p>
     *
     * @param event item in country combo box being selected
     */
    @FXML
    void onActionCountrySelected(ActionEvent event) {

        String countryName = customersByDivisionCountryCombo.getValue();

        ObservableList<String> divisionList = CustomerQuery.getAllCustomers().stream()
                .filter((item) -> item.getCountryName().equals(countryName))
                .map((item) -> item.getDivisionName())
                .distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        customersByDivisionDivisionCombo.setItems(divisionList);

    }

    /** When a division is selected the table view is populated with the information of all the customers that live
     * in the selected division.
     *
     * @param event item in division combo box being selected
     */
    @FXML
    void onActionDivisionSelected(ActionEvent event) {

        String divisionName = customersByDivisionDivisionCombo.getValue();

        customersByDivisionTableView.setItems(CustomerQuery.getAllCustomers().stream()
                .filter((item) -> item.getDivisionName().equals(divisionName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

    }


    /** When the reports view is loaded the type and contact combo boxes are populated with the types and contacts
     *  associated with all the meetings and the country combo box is populated with the countries associated with all
     *  the customers.
     *
     * @param url
     * @param resourceBundle
     */
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

        scheduleByContactApptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        scheduleByContactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        scheduleByContactTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        scheduleByContactDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        scheduleByContactStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        scheduleByContactEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        scheduleByContactCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        ObservableList<String> countryList = CustomerQuery.getAllCustomers().stream()
                .map((country) -> country.getCountryName())
                .distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        customersByDivisionCountryCombo.setItems(countryList);

        customersByDivisionCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customersByDivisionCustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersByDivisionAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customersByDivisionPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customersByDivisionPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));



    }

    /** Takes the type and month of a meeting as a parameter and returns the count of the meetings matching the
     *  parameters.
     *
     *  <p>This method contains a lambda expression that filters a list of all appointments and returns the count of
     *  appointments that have a type and occur in the month that matches the parameters. First it filters the list
     *  of all appointments to get a list of appointments with a type that matches the type parameter.  It then filters
     *  the list to get appointments that match the integer month parameter and returns a count of the appointments
     *  in the list.  This is used to populate the total appointments by type and month reports page with the
     *  necessary data.  </p>
     *
     * @param type meeting type
     * @param month month represented as an integer
     * @return long number of appointments that match the criteria
     */
    public long returnCountForTypeAndMonth(String type, int month) {

        long count = AppointmentQuery.getAllAppointments().stream()
                .filter((appt) -> appt.getType().equals(type))
                .filter((appt) -> appt.getStartTime().getMonthValue() == month)
                .count();

        return count;
    }
}
