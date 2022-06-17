package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomer implements Initializable {

    private Stage stage;
    private Parent scene;

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
}
