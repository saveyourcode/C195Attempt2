package model;

public class Country {

    private int CountryId;
    private String countryName;

    public Country(int countryId, String countryName) {
        CountryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return this.countryName;
    }
}
