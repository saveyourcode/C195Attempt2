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

    /** The customer class constructor.*/
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    /** Returns the returns the customer id.*/
    public int getCustomerId() {
        return customerId;
    }

    /** Sets the value of the customer id variable.*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    /** Sets the value of the customer name variable.*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** Returns the address.*/
    public String getAddress() {
        return address;
    }

    /** Sets the value of the address variable.*/
    public void setAddress(String address) {
        this.address = address;
    }

    /** Returns the postal code.*/
    public String getPostalCode() {
        return postalCode;
    }

    /** Sets the value of the postal code variable.*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Returns the phone number.*/
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** Sets the value of the phone number variable.*/
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Returns the division name.*/
    public String getDivisionName() {
        return divisionName;
    }

    /** Sets the value of the division name variable.*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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
        return this.customerName;
    }
}
