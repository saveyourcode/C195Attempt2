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
import java.time.ZoneId;


/** The First class to run when the program starts.*/
public class Main extends Application {

    /** Loads the login screen when the program starts.*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** When the program starts it makes a connection with the database, calls the method to load the login screen,
     * and when the program ends it closes the connection with the database.*/
    public static void main(String[] args) {

        DBConnection.makeConnection();

        launch(args);

        DBConnection.closeConnection();

        System.exit(0);
    }
}
