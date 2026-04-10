package com.fredo.book_travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String location;
    private String destination;
    private String DayOrNight;
    private long persons;

    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDayOrNight() {
        return DayOrNight;
    }

    public void setDayOrNight(String dayOrNight) {
        DayOrNight = dayOrNight;
    }

    public long getPersons() {
        return persons;
    }

    public void setPersons(long persons) {
        this.persons = persons;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonFormat( pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "BOOKING {" +
                "***User=" + user.getName() + '\'' +
                "***bookId=" + bookId +
                ", location='" + location + '\'' +
                ", destination='" + destination + '\'' +
                ", DayOrNight='" + DayOrNight + '\'' +
                ", persons=" + persons + '\'' +
                '}';
    }
}
