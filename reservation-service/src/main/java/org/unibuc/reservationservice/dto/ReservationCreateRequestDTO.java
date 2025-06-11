package org.unibuc.reservationservice.dto;

public class ReservationCreateRequestDTO {
    private Long yogaClassId;

    public ReservationCreateRequestDTO() {}

    public ReservationCreateRequestDTO(Long yogaClassId) {
        this.yogaClassId = yogaClassId;
    }

    public Long getYogaClassId() {
        return yogaClassId;
    }

    public void setYogaClassId(Long yogaClassId) {
        this.yogaClassId = yogaClassId;
    }
}
