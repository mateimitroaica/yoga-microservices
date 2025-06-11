package org.unibuc.reservationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibuc.reservationservice.dto.ReservationCreateRequestDTO;
import org.unibuc.reservationservice.dto.ReservationDTO;
import org.unibuc.reservationservice.security.JwtUtil;
import org.unibuc.reservationservice.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtUtil jwtUtil;

    public ReservationController(ReservationService reservationService,
                                 JwtUtil jwtUtil) {
        this.reservationService = reservationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationCreateRequestDTO request,
                                                            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer "
        ReservationDTO reservation = reservationService.createReservation(request.getYogaClassId(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getUserReservations(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<ReservationDTO> reservations = reservationService.getAllReservationsForUser(token);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/class/{yogaClassId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsForClass(@PathVariable Long yogaClassId,
                                                                        @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<String> authorities = jwtUtil.extractAuthorities(token);

        if (!authorities.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<ReservationDTO> reservations = reservationService.getReservationsForClass(yogaClassId);
        return ResponseEntity.ok(reservations);
    }
}
