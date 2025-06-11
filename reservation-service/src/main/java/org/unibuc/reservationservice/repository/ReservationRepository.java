package org.unibuc.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibuc.reservationservice.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserEmail(String email);
    List<Reservation> findByYogaClassId(Long yogaClassId);

    Boolean existsByUserEmailAndYogaClassId(String userEmail, Long yogaClassId);
}
