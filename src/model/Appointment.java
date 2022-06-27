package model;

import DBQuery.AppointmentQuery;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }





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

            if ((checkedAppt.getStartTime().isAfter(appointment.getStartTime()) || checkedAppt.getStartTime().isEqual(appointment.getStartTime())
            && (checkedAppt.getEndTime().isBefore(appointment.getEndTime())))) {
                return true;
            }

            if ((checkedAppt.getEndTime().isAfter(appointment.getStartTime())) && ((checkedAppt.getEndTime().isBefore(appointment.getEndTime()))
            || (checkedAppt.getEndTime().isEqual(appointment.getEndTime())))) {
                return true;
            }

            if (((checkedAppt.getStartTime().isBefore(appointment.getStartTime())) || (checkedAppt.getStartTime().isEqual(appointment.getStartTime())))
            && ((checkedAppt.getEndTime().isAfter(appointment.getEndTime())) || (checkedAppt.getEndTime().isEqual(appointment.getEndTime())))) {
                return true;
            }
        }

        return false;
    }
}
