package org.unibuc.reservationservice.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private String userEmail;
    private Long yogaClassId;
    private LocalDateTime reservationDateTime;

    public ReservationDTO(){}

    public ReservationDTO(Long id, String userEmail, Long yogaClassId, LocalDateTime reservationDateTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.yogaClassId = yogaClassId;
        this.reservationDateTime = reservationDateTime;
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

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }
}
