package org.unibuc.yogaclassservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.unibuc.yogaclassservice.entity.YogaClass;
import org.unibuc.yogaclassservice.entity.YogaClassType;

import java.time.LocalDate;

@Repository
public interface YogaClassRepository extends JpaRepository<YogaClass, Long> {

}
