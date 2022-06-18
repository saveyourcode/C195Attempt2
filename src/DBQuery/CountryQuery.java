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
}
