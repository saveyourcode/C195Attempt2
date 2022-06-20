package controller;

import DBQuery.CountryQuery;
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

    @FXML
    void onActionModifyCustomerCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyCustomerSave(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
