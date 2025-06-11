package org.unibuc.yogaclassservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibuc.yogaclassservice.dto.YogaClassCreateRequestDTO;
import org.unibuc.yogaclassservice.dto.YogaClassDTO;
import org.unibuc.yogaclassservice.entity.YogaClass;
import org.unibuc.yogaclassservice.service.YogaClassService;

import java.util.List;

@RestController
@RequestMapping("/api/yoga-classes")
public class YogaClassController {
    private final YogaClassService yogaClassService;

    public YogaClassController(YogaClassService yogaClassService) {
        this.yogaClassService = yogaClassService;
    }

    @PostMapping
    public ResponseEntity<YogaClassDTO> create(@RequestBody YogaClassCreateRequestDTO dto) {
        YogaClassDTO created = yogaClassService.createClass(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<YogaClassDTO>> getAllClasses() {
        return ResponseEntity.ok(yogaClassService.getAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<YogaClassDTO> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(yogaClassService.getClassById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<YogaClassDTO> updateClass(@PathVariable Long id,
                                                    @RequestBody YogaClassCreateRequestDTO request) {
        return ResponseEntity.ok(yogaClassService.updateClass(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        yogaClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
