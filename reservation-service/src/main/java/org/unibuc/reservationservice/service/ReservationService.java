package org.unibuc.reservationservice.service;

import org.unibuc.reservationservice.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(Long yogaClassId, String token);
    List<ReservationDTO> getAllReservationsForUser(String token);
    List<ReservationDTO> getReservationsForClass(Long yogaClassId);
}
