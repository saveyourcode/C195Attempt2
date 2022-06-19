package main;

import DBQuery.*;
import DBConnect.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

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
//        for (Customer cust: CustomerQuery.getAllCustomers()) {
//            System.out.println(cust.getCustomerName() + " " + cust.getDivisionName() + " " + cust.getCountryName());
//        }
//        System.out.println(CountryQuery.getCountryName(3));
        DBConnection.closeConnection();
    }
}
