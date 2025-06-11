package org.unibuc.yogaclassservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.unibuc.yogaclassservice.dto.YogaInstructorDTO;

@FeignClient(name = "instructor-service")
public interface InstructorClient {

    @GetMapping("/api/instructors/name")
    YogaInstructorDTO getByName(@RequestParam String firstName,
                                @RequestParam String lastName);
    @GetMapping("/api/instructors/{id}")
    YogaInstructorDTO getInstructorById(@PathVariable Long id);
}
