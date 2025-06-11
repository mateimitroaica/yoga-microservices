package org.unibuc.subscriptionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.unibuc.subscriptionservice.dto.StudioDTO;

@FeignClient(name = "studio-service")
public interface StudioClient {
    @GetMapping("/api/studios/name")
    StudioDTO getByName(@RequestParam String name);
}
