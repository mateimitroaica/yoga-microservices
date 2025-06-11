package org.unibuc.instructorservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.unibuc.instructorservice.domain.YogaInstructor;
import org.unibuc.instructorservice.dto.YogaInstructorCreateRequest;
import org.unibuc.instructorservice.dto.YogaInstructorDTO;
import org.unibuc.instructorservice.dto.YogaInstructorUpdateRequest;
import org.unibuc.instructorservice.mapper.YogaInstructorMapper;
import org.unibuc.instructorservice.repository.YogaIntrusctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YogaInstructorServiceImpl implements YogaInstructorService {

    private final YogaIntrusctorRepository instructorRepository;

    public YogaInstructorServiceImpl(YogaIntrusctorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public YogaInstructorDTO createInstructor(YogaInstructorCreateRequest request) {
        instructorRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Instructor with this name already exists.");
                });

        YogaInstructor instructor = YogaInstructorMapper.fromCreateRequest(request);
        return YogaInstructorMapper.toDto(instructorRepository.save(instructor));
    }

    @Override
    public YogaInstructorDTO updateInstructor(Long id, YogaInstructorUpdateRequest request) {
        YogaInstructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found with ID: " + id));

        String newFirstName = request.getFirstName() != null ? request.getFirstName() : instructor.getFirstName();
        String newLastName = request.getLastName() != null ? request.getLastName() : instructor.getLastName();

        // Check if there's another instructor with the same full name
        instructorRepository.findByFirstNameAndLastName(newFirstName, newLastName).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new IllegalArgumentException("Another instructor already exists with the name: " + newFirstName + " " + newLastName);
            }
        });

        // Proceed with the update
        if (request.getFirstName() != null) instructor.setFirstName(request.getFirstName());
        if (request.getLastName() != null) instructor.setLastName(request.getLastName());
        if (request.getAge() != null) instructor.setAge(request.getAge());
        if (request.getGender() != null) instructor.setGender(request.getGender());

        return YogaInstructorMapper.toDto(instructorRepository.save(instructor));
    }


    @Override
    public void deleteInstructor(Long id) {
        YogaInstructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found with ID: " + id));
        instructorRepository.delete(instructor);
    }

    @Override
    public YogaInstructorDTO getInstructorById(Long id) {
        YogaInstructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found with ID: " + id));
        return YogaInstructorMapper.toDto(instructor);
    }

    @Override
    public List<YogaInstructorDTO> getAllInstructors() {
        return instructorRepository.findAll()
                .stream()
                .map(YogaInstructorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<YogaInstructorDTO> findByFullName(String firstName, String lastName) {
        return instructorRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(YogaInstructorMapper::toDto);
    }
}
