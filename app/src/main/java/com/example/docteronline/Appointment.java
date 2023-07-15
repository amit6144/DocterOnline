package com.example.docteronline;

public class Appointment {

    private String patientName;
    private String patientMobileNumber;
    private String patientEmail;
    private String appointmentDate;
    private String appointmentTime;
    public  Appointment(){};
    public Appointment(String patientName, String patientMobileNumber, String patientEmail, String appointmentDate, String appointmentTime) {
        this.patientName = patientName;
        this.patientMobileNumber = patientMobileNumber;
        this.patientEmail = patientEmail;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobileNumber() {
        return patientMobileNumber;
    }

    public void setPatientMobileNumber(String patientMobileNumber) {
        this.patientMobileNumber = patientMobileNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public String getAppointmentDateTime() {
        return appointmentDate + " - " + appointmentTime;
    }
    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
