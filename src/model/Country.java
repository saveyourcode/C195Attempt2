package model;

/** Class that holds the methods and data for the Country class.*/
public class Country {

    private int CountryId;
    private String countryName;

    /** The country class constructor*/
    public Country(int countryId, String countryName) {
        CountryId = countryId;
        this.countryName = countryName;
    }

    /** Returns the country id.*/
    public int getCountryId() {
        return CountryId;
    }

    /** Sets the value of the country id variable.*/
    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    /** Returns the country name.*/
    public String getCountryName() {
        return countryName;
    }

    /** Sets the value of the country name variable.*/
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return this.countryName;
    }
}
