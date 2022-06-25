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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DBConnection.makeConnection();

//        CustomerQuery.insertCustomer("Harry Potter", "4 Privet Drive", "GU95", "020-8547-1221", 101);
//
//        CustomerQuery.insertCustomer("Albus Dumbledore", "Hogwarts", "GU95", "020-8547-1221", 101);

//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime later = LocalDateTime.now().plusHours(1);
//
//        AppointmentQuery.insertAppointment("appointment1" , "first appointment" , "conference room", "informational", now, later, 1, 1, 1 );
//
//        LocalDateTime nower = LocalDateTime.now().plusMinutes(15);
//        LocalDateTime laterer = LocalDateTime.now().plusMinutes(35);
//
//        AppointmentQuery.insertAppointment("appointment2" , "second appointment" , "office", "planning", nower, laterer, 1, 1, 1 );
//
//        LocalDateTime nowest = LocalDateTime.now().plusHours(6);
//        LocalDateTime laterest = LocalDateTime.now().plusHours(7);
//
//
//        AppointmentQuery.insertAppointment("appointment3" , "third appointment" , "media room", "presentation", nowest, laterest, 1, 1, 1 );

//        LocalDateTime now = LocalDateTime.of(2022, 11, 11, 11, 11, 11);
//        LocalDateTime later = LocalDateTime.of(2022, 12, 12, 12, 12, 12);
//
//        Appointment appt1 = new Appointment(1, "title", "description", "location", "contact", "type", now, later, 1, 1);
//        Appointment appt2 =  new Appointment(1, "title", "description", "location", "contact", "type", now, later, 1, 1);
//
//        System.out.println(appt1.equals(appt2));


        launch(args);

        DBConnection.closeConnection();

        System.exit(0);
    }
}
