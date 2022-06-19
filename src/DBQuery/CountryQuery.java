package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {

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
