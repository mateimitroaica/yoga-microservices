package org.unibuc.studioservice.service;

import org.springframework.web.multipart.MultipartFile;
import org.unibuc.studioservice.dto.StudioCreateRequest;
import org.unibuc.studioservice.dto.StudioDTO;
import org.unibuc.studioservice.dto.StudioUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface StudioService {
    StudioDTO createStudio(StudioCreateRequest request);
    StudioDTO getStudioById(Long id);

    StudioDTO updateStudio(Long id, StudioUpdateRequest request);

    Optional<StudioDTO> findByName(String name);

    List<StudioDTO> getAllStudios();

    void deleteStudio(Long id);
}
