package org.unibuc.subscriptionservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibuc.subscriptionservice.dto.SubscriptionCreateRequestDTO;
import org.unibuc.subscriptionservice.dto.SubscriptionDTO;
import org.unibuc.subscriptionservice.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionCreateRequestDTO request,
                                                              @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        SubscriptionDTO dto = subscriptionService.createSubscription(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptionsForUser(token);
        return ResponseEntity.ok(subscriptions);
    }
}
