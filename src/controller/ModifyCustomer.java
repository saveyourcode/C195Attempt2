package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModifyCustomer {

    @FXML
    private TextField modifyCustomerAddressText;

    @FXML
    private ComboBox<?> modifyCustomerCountryCombo;

    @FXML
    private ComboBox<?> modifyCustomerDivisionCombo;

    @FXML
    private TextField modifyCustomerIDText;

    @FXML
    private TextField modifyCustomerNameText;

    @FXML
    private TextField modifyCustomerPhoneNumberText;

    @FXML
    private TextField modifyCustomerPostalCodeText;

    @FXML
    void onActionModifyCustomerCancel(ActionEvent event) {

    }

    @FXML
    void onActionModifyCustomerSave(ActionEvent event) {

    }
}
