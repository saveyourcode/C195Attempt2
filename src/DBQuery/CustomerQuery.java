package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerQuery {

    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> returnedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, \n" +
                    "customers.Postal_Code, customers.Phone, customers.Division_ID, countries.Country, \n" +
                    "first_level_divisions.Division \n" +
                    "FROM ((customers INNER JOIN first_level_divisions \n" +
                    "ON customers.Division_ID = first_level_divisions.Division_ID)\n" +
                    "INNER JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID);";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int customerId = results.getInt("Customer_ID");
                String customerName = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postalCode = results.getString("Postal_Code");;
                String phoneNumber = results.getString("Phone");;
                String divisionName = results.getString("Division");
                String countryName = results.getString("Country");
                returnedList.add(new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionName,
                        countryName));
            }

        } catch (SQLException e) {

        }

        return returnedList;

    }
}
