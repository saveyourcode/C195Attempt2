package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class that holds the methods that query the countries table in the database.*/
public abstract class CountryQuery {

    /** Returns an observable list containing objects that hold all the information for the countries table.
     *
     * @return an observable list of country objects
     */
    public static ObservableList<Country> getAllCountries() {

        ObservableList<Country> returnedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Country country = new Country(countryId, countryName);
                returnedList.add(country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnedList;
    }

    /** Returns the country name that matches the country id that is passed as a parameter.
     *
     * @param countryId int countryId
     * @return String countryName that matches the countryId
     */
    public static String getCountryName(int countryId) {

        try {

            String sql = "SELECT Country FROM countries WHERE Country_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, countryId);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                return results.getString("Country");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return "empty";

    }
}
