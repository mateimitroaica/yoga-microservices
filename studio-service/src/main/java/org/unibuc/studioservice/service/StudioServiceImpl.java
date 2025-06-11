package org.unibuc.studioservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unibuc.studioservice.dto.StudioCreateRequest;
import org.unibuc.studioservice.dto.StudioDTO;
import org.unibuc.studioservice.dto.StudioUpdateRequest;
import org.unibuc.studioservice.entity.Studio;
import org.unibuc.studioservice.repository.StudioRepository;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final StudioRepository studioRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

//    @Override
//    public StudioDTO createStudio(StudioCreateRequest request) {
//        Studio studio = new Studio();
//        studio.setName(request.getName());
//        studio.setLocation(request.getLocation());
//
//        MultipartFile file = request.getFile();
//
//        if (file != null && !file.isEmpty()) {
//            try {
//                // Use project root + "uploads"
//                Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
//                Files.createDirectories(uploadDir); // creates if not exists
//
//                String originalFilename = Paths.get(file.getOriginalFilename()).getFileName().toString();
//                Path destination = uploadDir.resolve(originalFilename);
//
//                //  Transfer to real path
//                file.transferTo(destination.toFile());
//
//                studio.setPhotoPath(destination.toString());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Failed to store file", e);
//            }
//        } else {
//            System.out.println("No file uploaded or file is empty.");
//        }
//
//        Studio saved = studioRepository.save(studio);
//        return toDto(saved);
//    }

    @Override
    public StudioDTO createStudio(StudioCreateRequest request) {
        Optional<Studio> existingStudio = studioRepository.findByName(request.getName());
        if (existingStudio.isPresent()) {
            throw new IllegalArgumentException("Studio with name '" + request.getName() + "' already exists.");
        }

        Studio studio = new Studio();
        studio.setName(request.getName());
        studio.setLocation(request.getLocation());

        MultipartFile file = request.getFile();

        if (file != null && !file.isEmpty()) {
            try {
                Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
                Files.createDirectories(uploadDir);

                String originalFilename = Paths.get(file.getOriginalFilename()).getFileName().toString();
                Path destination = uploadDir.resolve(originalFilename);

                file.transferTo(destination.toFile());

                studio.setPhotoPath(destination.toString());

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to store file", e);
            }
        } else {
            System.out.println("No file uploaded or file is empty.");
        }

        Studio saved = studioRepository.save(studio);
        return toDto(saved);
    }


    @Override
    public StudioDTO getStudioById(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        StudioDTO dto = new StudioDTO();
        dto.setId(studio.getId());
        dto.setName(studio.getName());
        dto.setLocation(studio.getLocation());
        dto.setPhotoPath(studio.getPhotoPath());

        if (studio.getPhotoPath() != null) {
            dto.setPhotoUrl("/uploads/" + studio.getPhotoPath());
        }

        return dto;
    }

    @Override
    public StudioDTO updateStudio(Long id, StudioUpdateRequest request) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Studio not found with id: " + id));

        // Check for name uniqueness if it's being changed
        String newName = request.getName();
        if (newName != null && !newName.equals(studio.getName())) {
            boolean nameTaken = studioRepository.findByName(newName)
                    .filter(existing -> !existing.getId().equals(id))
                    .isPresent();

            if (nameTaken) {
                throw new IllegalArgumentException("Another studio with name '" + newName + "' already exists.");
            }

            studio.setName(newName);
        }

        // Update location
        if (request.getLocation() != null) {
            studio.setLocation(request.getLocation());
        }

        // Handle photo file upload
        MultipartFile file = request.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
                Files.createDirectories(uploadDir);

                Path filePath = uploadDir.resolve(fileName);
                file.transferTo(filePath.toFile());

                studio.setPhotoPath(filePath.toString());

            } catch (IOException e) {
                throw new RuntimeException("Failed to save photo", e);
            }
        }

        Studio updated = studioRepository.save(studio);
        return toDto(updated);
    }


    @Override
    public Optional<StudioDTO> findByName(String name) {
        return studioRepository.findByName(name).map(this::toDto);
    }

    @Override
    public List<StudioDTO> getAllStudios() {
        return studioRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStudio(Long id) {
        studioRepository.deleteById(id);
    }

    private StudioDTO toDto(Studio studio) {
        if (studio == null) {
            return null;
        }

        String baseUrl = "http://localhost:8080/uploads/";
        String photoUrl = null;

        if (studio.getPhotoPath() != null && !studio.getPhotoPath().isBlank()) {
            String filename = Paths.get(studio.getPhotoPath()).getFileName().toString();
            photoUrl = baseUrl + filename;
        }

        return new StudioDTO(
                studio.getId(),
                studio.getName(),
                studio.getLocation(),
                studio.getPhotoPath(),
                photoUrl
        );
    }
}