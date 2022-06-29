package controller;

import DBQuery.CountryQuery;
import DBQuery.CustomerQuery;
import DBQuery.DivisionQuery;
import javafx.collections.FXCollections;
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
import model.Division;
import utility.AlertMessages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/** CLass that holds all of the methods that control the add customer view.*/
public class AddCustomer implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private TextField addCustomerAddressText;

    @FXML
    private ComboBox<Country> addCustomerCountryCombo;

    @FXML
    private ComboBox<Division> addCustomerDivisionCombo;

    @FXML
    private TextField addCustomerIDText;

    @FXML
    private TextField addCustomerNameText;

    @FXML
    private TextField addCustomerPhoneNumberText;

    @FXML
    private TextField addCustomerPostalCodeText;

    /** Returns the user to the Startpage when pressed.*/
    @FXML
    void onActionAddCustomerCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Collects data input by the user to add a new customer to the database*/
    @FXML
    void onActionAddCustomerSave(ActionEvent event) throws IOException {

        try {

            String customerName = addCustomerNameText.getText();
            String address = addCustomerAddressText.getText();
            String postalCode = addCustomerPostalCodeText.getText();
            String phoneNumber = addCustomerPhoneNumberText.getText();
            int divisionId = (addCustomerDivisionCombo.getValue().getDivisionId());

            CustomerQuery.insertCustomer(customerName, address, postalCode, phoneNumber, divisionId);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch(NullPointerException e) {
            AlertMessages.warningAlert("Please select a country and division");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /** When the add customer view is loaded the country and division combo box are populated with all of the
     * available countries and divisions respectively.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCustomerCountryCombo.setItems(CountryQuery.getAllCountries());

        addCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions());

    }

    /** When a country is selected in the country combo box the division combo box is populated with only the divisions
     * that are located in the selected country.*/
    @FXML
    void onActionCountrySelected(ActionEvent event) {

        Country country = addCustomerCountryCombo.getValue();

        if (country != null) {
            addCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions().stream()
                    .filter((object) -> object.getCountryId() == country.getCountryId())
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }

    }
}
