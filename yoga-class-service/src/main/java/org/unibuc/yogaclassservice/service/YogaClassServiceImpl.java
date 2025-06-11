package org.unibuc.yogaclassservice.service;

import org.springframework.stereotype.Service;
import org.unibuc.yogaclassservice.dto.StudioDTO;
import org.unibuc.yogaclassservice.dto.YogaClassCreateRequestDTO;
import org.unibuc.yogaclassservice.dto.YogaClassDTO;
import org.unibuc.yogaclassservice.dto.YogaInstructorDTO;
import org.unibuc.yogaclassservice.entity.YogaClass;
import org.unibuc.yogaclassservice.exception.YogaClassNotFoundException;
import org.unibuc.yogaclassservice.helper.YogaClassEnrichmentService;
import org.unibuc.yogaclassservice.mapper.YogaClassMapper;
import org.unibuc.yogaclassservice.repository.YogaClassRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class YogaClassServiceImpl implements YogaClassService {
    private final YogaClassRepository yogaClassRepository;
    private final YogaClassMapper yogaClassMapper;
    private final YogaClassEnrichmentService yogaClassEnrichmentService;


    public YogaClassServiceImpl(YogaClassRepository yogaClassRepository,
                                YogaClassMapper yogaClassMapper,
                                YogaClassEnrichmentService yogaClassEnrichmentService) {
        this.yogaClassRepository = yogaClassRepository;
        this.yogaClassMapper = yogaClassMapper;
        this.yogaClassEnrichmentService = yogaClassEnrichmentService;
    }


    @Override
    public YogaClassDTO createClass(YogaClassCreateRequestDTO dto) {
        StudioDTO studio = yogaClassEnrichmentService.fetchStudio(dto.getStudioName());
        YogaInstructorDTO instructor = yogaClassEnrichmentService.fetchInstructor(dto.getInstructorFullName());

        String studioName = yogaClassEnrichmentService.getStudioName(studio.getId());
        String instructorName = yogaClassEnrichmentService.getInstructorFullName(instructor.getId());

        YogaClass yogaClass = yogaClassMapper.toEntity(dto);
        yogaClass.setStudioId(studio.getId());
        yogaClass.setInstructorId(instructor.getId());

        return yogaClassMapper.toDto(yogaClassRepository.save(yogaClass), studioName, instructorName);
    }

    @Override
    public List<YogaClassDTO> getAllClasses() {
        List<YogaClass> classes = yogaClassRepository.findAll();

        return classes.stream()
                .map(yogaClass -> {
                    String studioName = yogaClassEnrichmentService.getStudioName(yogaClass.getStudioId());
                    String instructorName = yogaClassEnrichmentService.getInstructorFullName(yogaClass.getInstructorId());
                    return yogaClassMapper.toDto(yogaClass, studioName, instructorName);
                })
                .collect(Collectors.toList());
    }

    @Override
    public YogaClassDTO getClassById(Long id) {
        YogaClass yogaClass = yogaClassRepository.findById(id)
                .orElseThrow(() -> new YogaClassNotFoundException(id));

        String studioName = yogaClassEnrichmentService.getStudioName(yogaClass.getStudioId());
        String instructorName = yogaClassEnrichmentService.getInstructorFullName(yogaClass.getInstructorId());

        return yogaClassMapper.toDto(yogaClass, studioName, instructorName);
    }

    @Override
    public void deleteClass(Long id) {
        if (!yogaClassRepository.existsById(id)) {
            throw new YogaClassNotFoundException(id);
        }
        yogaClassRepository.deleteById(id);
    }

    @Override
    public YogaClassDTO updateClass(Long id, YogaClassCreateRequestDTO dto) {
        YogaClass existing = yogaClassRepository.findById(id)
                .orElseThrow(() -> new YogaClassNotFoundException(id));

        StudioDTO studio = yogaClassEnrichmentService.fetchStudio(dto.getStudioName());
        YogaInstructorDTO instructor = yogaClassEnrichmentService.fetchInstructor(dto.getInstructorFullName());

        String studioName = yogaClassEnrichmentService.getStudioName(studio.getId());
        String instructorName = yogaClassEnrichmentService.getInstructorFullName(instructor.getId());

        existing.setName(dto.getName());
        existing.setTimeAndDate(dto.getTimeAndDate());
        existing.setPrice(dto.getPrice());
        existing.setYogaStyleType(dto.getYogaStyleType());
        existing.setStudioId(studio.getId());
        existing.setInstructorId(instructor.getId());

        return yogaClassMapper.toDto(yogaClassRepository.save(existing), studioName, instructorName);
    }


}
