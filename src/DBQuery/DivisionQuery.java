package DBQuery;

import DBConnect.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class that holds the methods that query the first_level_divisions table in the database.*/
public abstract class DivisionQuery {

    /** Returns an observable list containing objects that hold all the information for the first_level_divisions table.*/
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

    /** Returns the division name that matches the division id that is passed as a parameter.*/
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

    /** Returns the division id that matches the division name that is passed as a parameter.*/
    public static int getDivisionId(String divisionName) throws SQLException {

        String sql = "SELECT Division_ID FROM first_level_divisions Where Division = ?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, divisionName);
        ResultSet results = statement.executeQuery();
        while(results.next()) {
            return results.getInt("Division_ID");
        }

        return -1;

    }
}
