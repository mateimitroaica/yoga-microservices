package org.unibuc.yogaclassservice.service;

import org.unibuc.yogaclassservice.dto.YogaClassCreateRequestDTO;
import org.unibuc.yogaclassservice.dto.YogaClassDTO;
import org.unibuc.yogaclassservice.entity.YogaClass;

import java.util.List;

public interface YogaClassService {
    YogaClassDTO createClass(YogaClassCreateRequestDTO dto);
    List<YogaClassDTO> getAllClasses();
    YogaClassDTO getClassById(Long id);
    void deleteClass(Long id);
    YogaClassDTO updateClass(Long id, YogaClassCreateRequestDTO dto);
}
