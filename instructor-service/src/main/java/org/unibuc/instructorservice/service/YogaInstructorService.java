package org.unibuc.instructorservice.service;

import org.unibuc.instructorservice.dto.YogaInstructorCreateRequest;
import org.unibuc.instructorservice.dto.YogaInstructorDTO;
import org.unibuc.instructorservice.dto.YogaInstructorUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface YogaInstructorService {
    YogaInstructorDTO createInstructor(YogaInstructorCreateRequest request);
    YogaInstructorDTO updateInstructor(Long id, YogaInstructorUpdateRequest request);
    void deleteInstructor(Long id);
    YogaInstructorDTO getInstructorById(Long id);
    List<YogaInstructorDTO> getAllInstructors();
    Optional<YogaInstructorDTO> findByFullName(String firstName, String lastName);
}
