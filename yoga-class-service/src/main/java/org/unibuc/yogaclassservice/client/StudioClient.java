package org.unibuc.yogaclassservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.unibuc.yogaclassservice.dto.StudioDTO;
import org.unibuc.yogaclassservice.dto.YogaInstructorDTO;

@FeignClient(name = "studio-service")
public interface StudioClient {

    @GetMapping("/api/studios/name")
    StudioDTO getStudioByName(@RequestParam("name") String name);
    @GetMapping("/api/studios/{id}")
    StudioDTO getById(@PathVariable Long id);
}
