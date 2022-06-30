package model;

/** Class that holds the methods and data for the Division class.*/
public class Division {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /** The Division class constructor.*/
    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /** Returns the division id.*/
    public int getDivisionId() {
        return divisionId;
    }

    /** Sets the value of the division id variable.*/
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Returns the division name.*/
    public String getDivisionName() {
        return divisionName;
    }

    /** Sets the value of the division name variable.*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /** Returns the country id.*/
    public int getCountryId() {
        return countryId;
    }

    /** Sets the value of the country id variable.*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String toString() {
        return this.divisionName;
    }
}
