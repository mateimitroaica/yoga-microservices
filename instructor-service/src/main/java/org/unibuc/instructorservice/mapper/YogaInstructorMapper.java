package org.unibuc.instructorservice.mapper;

import org.unibuc.instructorservice.domain.YogaInstructor;
import org.unibuc.instructorservice.dto.YogaInstructorCreateRequest;
import org.unibuc.instructorservice.dto.YogaInstructorDTO;
import org.unibuc.instructorservice.dto.YogaInstructorUpdateRequest;

public class YogaInstructorMapper {
    public static YogaInstructorDTO toDto(YogaInstructor instructor) {
        YogaInstructorDTO dto = new YogaInstructorDTO();
        dto.setId(instructor.getId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setAge(instructor.getAge());
        dto.setGender(instructor.getGender());
        return dto;
    }

    public static YogaInstructor fromCreateRequest(YogaInstructorCreateRequest request) {
        YogaInstructor instructor = new YogaInstructor();
        instructor.setFirstName(request.getFirstName());
        instructor.setLastName(request.getLastName());
        instructor.setAge(request.getAge());
        instructor.setGender(request.getGender());
        return instructor;
    }

    public void updateEntity(YogaInstructor instructor, YogaInstructorUpdateRequest request) {
        if (request.getFirstName() != null) instructor.setFirstName(request.getFirstName());
        if (request.getLastName() != null) instructor.setLastName(request.getLastName());
        if (request.getAge() != null) instructor.setAge(request.getAge());
        if (request.getGender() != null) instructor.setGender(request.getGender());
    }
}
