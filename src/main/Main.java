package main;

import DBQuery.*;
import DBConnect.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        //launch(args);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now().plusHours(12);


        //AppointmentQuery.insertAppointment("test1", "test2", "test3", "test4", now, later, 1,1,1);

        //AppointmentQuery.updateAppointment(3, "Meeting" , "Meet Up", "Office", "Short Meeting" , now, later, 1, 1, 1);

        //AppointmentQuery.deleteAppointment(3);

        //CustomerQuery.insertCustomer("Bill Williams", "123 Fake Street", "84119", "555-3543", 4);

        //CustomerQuery.updateCustomer(4, "Bill Withers", "123 Fake Street", "55544", "555-5555", 4 );

        CustomerQuery.deleteCustomer(4);

        DBConnection.closeConnection();

        System.exit(0);
    }
}
