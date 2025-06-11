package org.unibuc.reservationservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    private Long yogaClassId;

    private LocalDateTime dateTime;

    public Reservation() {}

    public Reservation(Long id, String userEmail, Long yogaClassId, LocalDateTime dateTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.yogaClassId = yogaClassId;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getYogaClassId() {
        return yogaClassId;
    }

    public void setYogaClassId(Long yogaClassId) {
        this.yogaClassId = yogaClassId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
