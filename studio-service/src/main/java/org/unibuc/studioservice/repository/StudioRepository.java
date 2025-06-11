package org.unibuc.studioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibuc.studioservice.entity.Studio;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    Optional<Studio> findByName(String name);
}
