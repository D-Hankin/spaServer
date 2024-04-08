package org.spaserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Booking {
    
    @Id
    @Column(unique = true)
    private int bookingNumber;

    @NotEmpty(message = "You need to enter your name to complete a booking.")
    private String name;

    @NotEmpty(message = "You need to enter a valid phone number to complete a booking")
    private String phoneNumber;

    @NotEmpty(message = "You need to enter a valid email to complete a booking")
    @Email
    private String email;

    @NotEmpty(message = "No session type chosen.")
    private String sessionType;

    @NotEmpty(message = "No date chosen.")
    private String date;

    @NotEmpty(message = "No session chosen.")
    private String session;

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    




}
