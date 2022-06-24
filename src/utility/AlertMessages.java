package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessages {

    private static Alert alert;

    public static void warningAlert(String text) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void errorAlert(String text) {

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

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
