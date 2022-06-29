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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** CLass that holds all of the methods that control the login view.*/
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

    /** Takes the user inputted username and queries the database to chekc if the the username and password match, and
     * if they match it loads the Startpage, if they don't match it displays an error message. */
    @FXML
    void onActionLoginButton(ActionEvent event) throws IOException {

        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        if (Helper.checkPassword(username, password)) {

            recordLoginActivity(username, "Login Attempt Successful");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/StartPage.fxml"));
            loader.load();

            StartPage startPageController = loader.getController();
            startPageController.transferFromLogin();

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();


        } else {

            recordLoginActivity(username, "Login Attempt Unsuccessful");

            AlertMessages.errorAlert(bundle.getString("errorHead"), bundle.getString("errorBody"));
        }

    }

    /** Translates all text in the login screen to the appropriate language based on the user's location.*/
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

    /** Writes the username, date, time, and whether the login was successful or not to a file when a user attempts to login.*/
    public void recordLoginActivity(String username, String successfulOrNot) {

        try {

            FileWriter file = new FileWriter("login_activity.txt", true);
            PrintWriter writer = new PrintWriter(file);
            writer.println(username + "," + LocalDate.now() + "," + LocalTime.now() + "," + successfulOrNot);
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}