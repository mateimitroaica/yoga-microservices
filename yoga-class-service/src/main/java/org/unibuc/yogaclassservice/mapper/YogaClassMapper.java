package org.unibuc.yogaclassservice.mapper;

import org.springframework.stereotype.Component;
import org.unibuc.yogaclassservice.dto.YogaClassCreateRequestDTO;
import org.unibuc.yogaclassservice.dto.YogaClassDTO;
import org.unibuc.yogaclassservice.entity.YogaClass;

import java.time.format.DateTimeFormatter;

@Component
public class YogaClassMapper {

    public YogaClass toEntity(YogaClassCreateRequestDTO dto) {
        YogaClass yogaClass = new YogaClass();
        yogaClass.setName(dto.getName());
        yogaClass.setTimeAndDate(dto.getTimeAndDate());
        yogaClass.setPrice(dto.getPrice());
        yogaClass.setYogaStyleType(dto.getYogaStyleType());
        return yogaClass;
    }

    public YogaClassDTO toDto(YogaClass entity, String studioName, String instructorFullName) {
        YogaClassDTO dto = new YogaClassDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTimeAndDate(entity.getTimeAndDate());
        dto.setPrice(entity.getPrice());
        dto.setYogaStyleType(entity.getYogaStyleType());
        dto.setStudioId(entity.getStudioId());
        dto.setInstructorId(entity.getInstructorId());
        dto.setInstructorFullName(instructorFullName);
        dto.setStudioName(studioName);
        return dto;
    }

}
