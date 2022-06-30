package model;

/** Class that holds the methods and data for the Contact class.*/
public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    /** The contact class constructor*/
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** Returns the contact id.*/
    public int getContactId() {
        return contactId;
    }

    /** Sets the value of the contact id variable.*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Returns the contact name.*/
    public String getContactName() {
        return contactName;
    }

    /** Sets the value of the contact name variable.*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** Returns the email.*/
    public String getEmail() {
        return email;
    }

    /** Sets the value of the email variable.*/
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return this.contactName;
    }
}
