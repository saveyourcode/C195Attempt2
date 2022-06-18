package main;

import DBQuery.AppointmentQuery;
import DBQuery.CountryQuery;
import DBConnect.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DBConnection.makeConnection();
        launch(args);
        for (Appointment appointment: AppointmentQuery.getAllCustomers()) {
            System.out.println(appointment.getTitle());
        }
        DBConnection.closeConnection();
    }
}
