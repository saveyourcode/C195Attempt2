package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DivisionQuery {

    public static ObservableList<Division> getAllDivisions() {

        ObservableList<Division> returnedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int divisionId = results.getInt("Division_ID");
                String divisionName = results.getString("Division");
                int countryId = results.getInt("Country_ID");
                returnedList.add(new Division(divisionId, divisionName, countryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnedList;

    }

    public static String getDivisionName(int divisionId) {

        try {

            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, divisionId);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                return results.getString("Division");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return "empty";

    }
}
