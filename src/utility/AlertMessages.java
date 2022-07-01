package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** Class that holds the static methods for creating alert windows. */
public class AlertMessages {

    private static Alert alert;

    /** Creates a warning message window.*/
    /**
     *
     * @param text message displayed in the body
     */
    public static void warningAlert(String text) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates an error message window*/
    /**
     *
     * @param text message displayed in body
     */
    public static void errorAlert(String text) {

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates an error message window.*/
    /**
     *
     * @param head message displayed in header
     * @param body message displayed in body
     */
    public static void errorAlert(String head, String body) {

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(head);
        alert.setContentText(body);
        alert.showAndWait();
    }

    public static void informationAlert(String text) {

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates an information message window.*/
    /**
     *
     * @param head message displayed in the header
     * @param body message displayed in the body
     */
    public static void informationAlert(String head, String body) {

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(head);
        alert.setContentText(body);
        alert.showAndWait();
    }

    /** Creates a confirmation window and records the users choice*/
    /**
     *
     * @param text message that will be displayed
     * @return boolean
     */
    public static boolean confirmationAlert(String text) {

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        Optional<ButtonType> userChoice = alert.showAndWait();

        if (userChoice.isPresent() && userChoice.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }


    }


}
