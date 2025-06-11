package org.unibuc.instructorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibuc.instructorservice.domain.YogaInstructor;

import java.util.Optional;

@Repository
public interface YogaIntrusctorRepository extends JpaRepository<YogaInstructor, Long> {
    Optional<YogaInstructor> findByFirstNameAndLastName(String firstName, String lastName);

}
