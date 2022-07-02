package model;

/** Class that holds the methods and data for the Customer class.*/
public class Customer {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String divisionName;
    private String countryName;

    /** The customer class constructor.
     *
     * @param customerId int customerId
     * @param customerName String customerName
     * @param address String address
     * @param postalCode String postalCode
     * @param phoneNumber String phoneNumber
     * @param divisionName String divisionName
     * @param countryName String countryName
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    /** Returns the customer id.
     *
     * @return int customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Sets the value of the customer id variable.
     *
     * @param customerId int customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Returns the customerName.
     *
     * @return String customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /** Sets the value of the customer name variable.
     *
     * @param customerName String customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** Returns the address.
     *
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /** Sets the value of the address variable.
     *
     * @param address String address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Returns the postal code.
     *
     * @return String postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** Sets the value of the postal code variable.
     *
     * @param postalCode String postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Returns the phone number.
     *
     * @return String phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** Sets the value of the phone number variable.
     *
     * @param phoneNumber String phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Returns the division name.
     *
     * @return String divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /** Sets the value of the division name variable.
     *
     * @param divisionName String divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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
        return this.customerName;
    }
}
