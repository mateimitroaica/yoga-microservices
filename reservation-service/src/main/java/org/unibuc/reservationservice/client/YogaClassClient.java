package org.unibuc.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.unibuc.reservationservice.dto.YogaClassDTO;

@FeignClient(name="yoga-class-service")
public interface YogaClassClient {
    @GetMapping("/api/yoga-classes/{id}")
    YogaClassDTO getClassById(@PathVariable Long id);
}