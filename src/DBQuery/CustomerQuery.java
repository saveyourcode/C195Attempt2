package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Class that holds the methods that query the customers table in the database.*/
public abstract class CustomerQuery {

    /** Returns an observable list containing objects that hold all the information for the customers table.*/
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

    /** Inserts a new customer entry into the customers table.*/
    public static int insertCustomer(String customerName, String address, String postalCode,
                                     String phoneNumber, int divisionId) {
        try {

            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, address);
            statement.setString(3, postalCode);
            statement.setString(4, phoneNumber);
            statement.setInt(5, divisionId);

            return statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /** Updates the information of an existing entry in the customers table.*/
    public static int updateCustomer(int customerId, String customerName, String address, String postalCode,
                                     String phoneNumber, int divisionId) {

        try {

            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?" +
                    " WHERE Customer_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, address);
            statement.setString(3, postalCode);
            statement.setString(4, phoneNumber);
            statement.setInt(5, divisionId);
            statement.setInt(6, customerId);
            return statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /** Deletes an appointment with the matching appointment id from the customers table.*/
    public static int deleteCustomer(int customerId) {

        try {

            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, customerId);
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
