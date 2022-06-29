package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/** CLass that holds all of the methods that control the modify customer view.*/
public class ModifyCustomer implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private TextField modifyCustomerAddressText;

    @FXML
    private ComboBox<Country> modifyCustomerCountryCombo;

    @FXML
    private ComboBox<Division> modifyCustomerDivisionCombo;

    @FXML
    private TextField modifyCustomerIDText;

    @FXML
    private TextField modifyCustomerNameText;

    @FXML
    private TextField modifyCustomerPhoneNumberText;

    @FXML
    private TextField modifyCustomerPostalCodeText;

    /** Returns the user to the Startpage when pressed.*/
    @FXML
    void onActionModifyCustomerCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** An existing customer's information is updated in the database using the information that the user has inputted
     * in the combo boxes and text fields.*/
    @FXML
    void onActionModifyCustomerSave(ActionEvent event) throws IOException {

        try {

            int customerId = Integer.parseInt(modifyCustomerIDText.getText());
            String customerName = modifyCustomerNameText.getText();
            String address = modifyCustomerAddressText.getText();
            String postalCode = modifyCustomerPostalCodeText.getText();
            String phoneNumber = modifyCustomerPhoneNumberText.getText();
            int divisionId = modifyCustomerDivisionCombo.getValue().getDivisionId();

            CustomerQuery.updateCustomer(customerId, customerName, address, postalCode, phoneNumber, divisionId);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** The customer that is chosen to be modified is sent to the modify customer view from the start page and
     * the customer's data is used to populate the combo boxes and text fields.*/
    public void transferCustomer(Customer customer) {

        modifyCustomerIDText.setText(String.valueOf(customer.getCustomerId()));
        modifyCustomerNameText.setText(customer.getCustomerName());
        modifyCustomerAddressText.setText(customer.getAddress());
        modifyCustomerPostalCodeText.setText(customer.getPostalCode());
        modifyCustomerPhoneNumberText.setText(customer.getPhoneNumber());

        modifyCustomerCountryCombo.setItems(CountryQuery.getAllCountries());

        Country country = CountryQuery.getAllCountries().stream()
                .filter((object) -> object.getCountryName().equals(customer.getCountryName()))
                .findFirst().orElse(null);

        modifyCustomerCountryCombo.setValue(country);

        modifyCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions());

        Division division = DivisionQuery.getAllDivisions().stream()
                .filter((object) -> object.getDivisionName().equals(customer.getDivisionName()))
                .findFirst().orElse(null);

        modifyCustomerDivisionCombo.setValue(division);


//        modifyCustomerCountryCombo.setItems(CountryQuery.getAllCountries().stream()
//                .filter((object) -> object.getCountryName().equals(customer.getCountryName()))
//        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
//
//        modifyCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions().stream()
//                .filter((object) -> object.getDivisionName().equals(customer.getDivisionName()))
//                .collect(Collectors.toCollection(FXCollections::observableArrayList)));


    }

    /** When a country is selected the division combo box is populated with only the divisions that are in the
     * selected country.*/
    @FXML
    void onActionCountrySelected(ActionEvent event) {

        Country country = modifyCustomerCountryCombo.getValue();

        if (country != null) {
            modifyCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions().stream()
                    .filter((object) -> object.getCountryId() == country.getCountryId())
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }

    }
}
