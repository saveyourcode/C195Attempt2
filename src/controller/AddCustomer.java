package controller;

import DBQuery.CountryQuery;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    void onActionAddCustomerCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddCustomerSave(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCustomerCountryCombo.setItems(CountryQuery.getAllCountries());

        addCustomerDivisionCombo.setItems(DivisionQuery.getAllDivisions());

    }

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
