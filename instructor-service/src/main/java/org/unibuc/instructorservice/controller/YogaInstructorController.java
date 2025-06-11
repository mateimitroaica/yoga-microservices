package org.unibuc.instructorservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibuc.instructorservice.dto.YogaInstructorCreateRequest;
import org.unibuc.instructorservice.dto.YogaInstructorDTO;
import org.unibuc.instructorservice.dto.YogaInstructorUpdateRequest;
import org.unibuc.instructorservice.service.YogaInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class YogaInstructorController {

    private final YogaInstructorService instructorService;

    public YogaInstructorController(YogaInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<YogaInstructorDTO> createInstructor(@RequestBody YogaInstructorCreateRequest request) {
        YogaInstructorDTO created = instructorService.createInstructor(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<YogaInstructorDTO> updateInstructor(
            @PathVariable Long id,
            @RequestBody YogaInstructorUpdateRequest request) {
        YogaInstructorDTO updated = instructorService.updateInstructor(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<YogaInstructorDTO> getInstructorById(@PathVariable Long id) {
        YogaInstructorDTO instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }

    @GetMapping
    public ResponseEntity<List<YogaInstructorDTO>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/name")
    public ResponseEntity<YogaInstructorDTO> getByName(@RequestParam String firstName,
                                                       @RequestParam String lastName) {
        return instructorService.findByFullName(firstName, lastName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
