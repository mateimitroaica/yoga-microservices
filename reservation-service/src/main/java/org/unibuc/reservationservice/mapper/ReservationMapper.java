package org.unibuc.reservationservice.mapper;

import org.springframework.stereotype.Component;
import org.unibuc.reservationservice.dto.ReservationDTO;
import org.unibuc.reservationservice.entity.Reservation;

@Component
public class ReservationMapper {
    public ReservationDTO toDto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setUserEmail(reservation.getUserEmail());
        dto.setYogaClassId(reservation.getYogaClassId());
        dto.setReservationDateTime(reservation.getDateTime());
        return dto;
    }

    public Reservation toEntity(String userEmail, Long yogaClassId) {
        Reservation entity = new Reservation();
        entity.setUserEmail(userEmail);
        entity.setYogaClassId(yogaClassId);
        entity.setDateTime(java.time.LocalDateTime.now());
        return entity;
    }
}
