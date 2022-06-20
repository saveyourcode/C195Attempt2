package main;

import DBQuery.*;
import DBConnect.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DBConnection.makeConnection();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now().plusHours(4);

        AppointmentQuery.insertAppointment("title", "desc.", "office", "bill billton", "short", now, later, 1, 3 );



        DBConnection.closeConnection();

        System.exit(0);
    }
}
