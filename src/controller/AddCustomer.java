package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddCustomer {

    @FXML
    private TextField addCustomerAddressText;

    @FXML
    private ComboBox<?> addCustomerCountryCombo;

    @FXML
    private ComboBox<?> addCustomerDivisionCombo;

    @FXML
    private TextField addCustomerIDText;

    @FXML
    private TextField addCustomerNameText;

    @FXML
    private TextField addCustomerPhoneNumberText;

    @FXML
    private TextField addCustomerPostalCodeText;

    @FXML
    void onActionAddCustomerCancel(ActionEvent event) {

    }

    @FXML
    void onActionAddCustomerSave(ActionEvent event) {

    }
}
