package org.unibuc.studioservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibuc.studioservice.dto.StudioCreateRequest;
import org.unibuc.studioservice.dto.StudioDTO;
import org.unibuc.studioservice.dto.StudioUpdateRequest;
import org.unibuc.studioservice.service.StudioService;

import java.util.List;

@RestController
@RequestMapping("/api/studios")
public class StudioController {

    private final StudioService studioService;

    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StudioDTO> createStudio(@ModelAttribute StudioCreateRequest request) {
        System.out.println("---- CONTROLLER DEBUG ----");
        System.out.println("Name: " + request.getName());
        System.out.println("Location: " + request.getLocation());
        System.out.println("File: " + request.getFile());
        System.out.println("Request Content-Type: " + request.getFile().getContentType());
        System.out.println("Original filename: " + request.getFile().getOriginalFilename());
        System.out.println("File is empty: " + (request.getFile() == null ? "null" : request.getFile().isEmpty()));
        System.out.println("--------------------------");

        StudioDTO created = studioService.createStudio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studioService.getStudioById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StudioDTO> updateStudio(
            @PathVariable Long id,
            @ModelAttribute StudioUpdateRequest request) {
        StudioDTO updated = studioService.updateStudio(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/name")
    public ResponseEntity<StudioDTO> getByName(@RequestParam String name) {
        return studioService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudioDTO>> getAll() {
        return ResponseEntity.ok(studioService.getAllStudios());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudio(@PathVariable Long id) {
        studioService.deleteStudio(id);
        return ResponseEntity.noContent().build();
    }
}