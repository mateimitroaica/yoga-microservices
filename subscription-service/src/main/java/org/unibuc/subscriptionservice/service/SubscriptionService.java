package org.unibuc.subscriptionservice.service;

import org.unibuc.subscriptionservice.dto.SubscriptionCreateRequestDTO;
import org.unibuc.subscriptionservice.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionCreateRequestDTO request, String token);
    List<SubscriptionDTO> getSubscriptionsForUser(String token);
}
