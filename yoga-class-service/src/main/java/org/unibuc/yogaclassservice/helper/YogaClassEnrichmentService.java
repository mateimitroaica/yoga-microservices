package org.unibuc.yogaclassservice.helper;

import feign.FeignException;
import org.springframework.stereotype.Service;
import org.unibuc.yogaclassservice.client.InstructorClient;
import org.unibuc.yogaclassservice.client.StudioClient;
import org.unibuc.yogaclassservice.dto.StudioDTO;
import org.unibuc.yogaclassservice.dto.YogaInstructorDTO;
import org.unibuc.yogaclassservice.exception.InstructorNotFoundException;
import org.unibuc.yogaclassservice.exception.StudioNotFoundException;

import java.util.Arrays;

@Service
public class YogaClassEnrichmentService {

    private final InstructorClient instructorClient;
    private final StudioClient studioClient;

    public YogaClassEnrichmentService(InstructorClient instructorClient, StudioClient studioClient) {
        this.instructorClient = instructorClient;
        this.studioClient = studioClient;
    }

    public String getStudioName(Long studioId) {
        try {
            return studioClient.getById(studioId).getName();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    public String getInstructorFullName(Long instructorId) {
        try {
            YogaInstructorDTO dto = instructorClient.getInstructorById(instructorId);
            return dto.getFirstName() + " " + dto.getLastName();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    public StudioDTO fetchStudio(String studioName) {
        try {
            return studioClient.getStudioByName(studioName);
        } catch (FeignException.NotFound e) {
            throw new StudioNotFoundException(studioName);
        }
    }

    public YogaInstructorDTO fetchInstructor(String fullName) {
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Instructor full name must include first and last name.");
        }
        String firstName = parts[0];
        String lastName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        try {
            return instructorClient.getByName(firstName, lastName);
        } catch (FeignException.NotFound e) {
            throw new InstructorNotFoundException(fullName);
        }
    }
}
