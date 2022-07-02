package model;

/** Class that holds the methods and data for the Country class.*/
public class Country {

    private int CountryId;
    private String countryName;

    /** The country class constructor.
     *
     * @param countryId int countryId
     * @param countryName String countryName
     */
    public Country(int countryId, String countryName) {
        CountryId = countryId;
        this.countryName = countryName;
    }

    /** Returns the country id.
     *
     * @return int countryId
     */
    public int getCountryId() {
        return CountryId;
    }

    /** Sets the value of the country id variable.
     *
     * @param countryId int countryId
     */
    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    /** Returns the country name.
     *
     * @return String countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /** Sets the value of the country name variable.
     *
     * @param countryName String countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return this.countryName;
    }
}
