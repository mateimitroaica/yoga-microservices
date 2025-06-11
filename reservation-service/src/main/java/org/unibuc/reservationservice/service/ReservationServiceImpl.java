package org.unibuc.reservationservice.service;

import org.springframework.stereotype.Service;
import org.unibuc.reservationservice.dto.ReservationDTO;
import org.unibuc.reservationservice.entity.Reservation;
import org.unibuc.reservationservice.mapper.ReservationMapper;
import org.unibuc.reservationservice.repository.ReservationRepository;
import org.unibuc.reservationservice.security.JwtUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final JwtUtil jwtUtil;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper,
                                  JwtUtil jwtUtil) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ReservationDTO createReservation(Long yogaClassId, String token) {
        String email = jwtUtil.extractUsername(token);

        // Check if the reservation already exists
        if (reservationRepository.existsByUserEmailAndYogaClassId(email, yogaClassId)) {
            throw new IllegalStateException("Reservation already exists for this user and class.");
        }

        Reservation reservation = new Reservation();
        reservation.setUserEmail(email);
        reservation.setYogaClassId(yogaClassId);
        reservation.setDateTime(LocalDateTime.now());

        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toDto(saved);
    }

    @Override
    public List<ReservationDTO> getAllReservationsForUser(String token) {
        String email = jwtUtil.extractUsername(token);
        return reservationRepository.findAllByUserEmail(email)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDTO> getReservationsForClass(Long yogaClassId) {
        return reservationRepository.findByYogaClassId(yogaClassId)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }
}