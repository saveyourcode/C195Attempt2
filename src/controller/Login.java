package controller;

import DBQuery.UserQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.AlertMessages;
import utility.Helper;

import javax.swing.text.Utilities;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Initializable {

    private Stage stage;
    private Parent scene;
    private ResourceBundle bundle;

    @FXML
    private Button loginButtonLabel;

    @FXML
    private Label loginLocationLabel;

    @FXML
    private Label loginPasswordLabel;

    @FXML
    private TextField loginPasswordText;

    @FXML
    private Label loginSignInLabel;

    @FXML
    private Label loginUsernameLabel;

    @FXML
    private TextField loginUsernameText;

    @FXML
    private Label loginZoneIdText;

    @FXML
    void onActionLoginButton(ActionEvent event) throws IOException {

        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        if (Helper.checkPassword(username, password)) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/StartPage.fxml"));
            loader.load();

            StartPage startPageController = loader.getController();
            startPageController.transferFromLogin();

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

//            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
//            stage.setScene(new Scene(scene));
//            stage.show();

        } else {

            AlertMessages.errorAlert(bundle.getString("errorHead"), bundle.getString("errorBody"));
        }



//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/StartPage.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Locale french = new Locale("fr");
//
//        Locale.setDefault(french);

        bundle = ResourceBundle.getBundle("utility/translation", Locale.getDefault());

        loginSignInLabel.setText(bundle.getString("signIn"));
        loginUsernameLabel.setText(bundle.getString("username"));
        loginPasswordLabel.setText(bundle.getString("password"));
        loginButtonLabel.setText(bundle.getString("login"));
        loginLocationLabel.setText(bundle.getString("location"));
        loginZoneIdText.setText(String.valueOf(ZoneId.systemDefault()));

    }
}