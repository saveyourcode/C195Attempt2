package model;

import DBQuery.AppointmentQuery;

import java.time.LocalDateTime;
import java.util.Objects;

/** Class that holds the methods and data for the Appointment class.*/
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;

    /** The appointment class constructor.*/
    public Appointment(int appointmentId, String title, String description, String location, String contact, String type,
                       LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId) {

        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;

    }

    /** Returns the appointment id.*/
    public int getAppointmentId() {
        return appointmentId;
    }

    /** Sets the value of the appointment id variable.*/
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** Returns the title.*/
    public String getTitle() {
        return title;
    }

    /** Sets the value of the title variable.*/
    public void setTitle(String title) {
        this.title = title;
    }

    /** Returns the description.*/
    public String getDescription() {
        return description;
    }

    /** Sets the value of the description variable.*/
    public void setDescription(String description) {
        this.description = description;
    }

    /** Returns the location.*/
    public String getLocation() {
        return location;
    }

    /** Sets the value of the location variable.*/
    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    /** Sets the value of the type variable.*/
    public void setType(String type) {
        this.type = type;
    }

    /** Returns the startTime.*/
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /** Sets the value of the start time variable.*/
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /** Returns the endTime.*/
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /** Sets the value of the end time variable.*/
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    /** Sets the value of the customer id variable.*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Returns the user id.*/
    public int getUserId() {
        return userId;
    }

    /** Sets the value of the user id variable.*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Returns the contact name.*/
    public String getContact() {
        return contact;
    }

    /** Sets the value of the contact name variable.*/
    public void setContact(String contact) {
        this.contact = contact;
    }

    /** Takes a new appointment as a parameter and checks it against all existing appointments for an overlap between
     * the appointments.*/
    public static boolean checkAppointmentConflict(Appointment checkedAppt) {
        for (Appointment appointment: AppointmentQuery.getAllAppointments()) {
            if ((checkedAppt.getCustomerId() != appointment.getCustomerId())) {
                continue;
            }

            if (checkedAppt.getAppointmentId() == appointment.getAppointmentId()) {
                continue;
            }

            if(!(checkedAppt.getStartTime().toLocalDate().isEqual(appointment.getStartTime().toLocalDate()))) {
                continue;
            }

            if ((checkedAppt.getStartTime().isAfter(appointment.getStartTime())) && (checkedAppt.getStartTime()
                    .isBefore(appointment.getEndTime()))) {

                return true;
            }

            if ((checkedAppt.getEndTime().isAfter(appointment.getStartTime())) && (checkedAppt.getEndTime()
                    .isBefore(appointment.getEndTime()))) {

                return true;
            }

            if ((checkedAppt.getStartTime().isBefore(appointment.getStartTime())) && (checkedAppt.getEndTime()
                    .isAfter(appointment.getEndTime()))) {

                return true;
            }

            if ((checkedAppt.getStartTime().isEqual(appointment.getStartTime())) || (checkedAppt.getEndTime()
                    .isEqual(appointment.getEndTime()))) {

                return true;
            }

        }

        return false;
    }
}
