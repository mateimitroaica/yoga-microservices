package org.unibuc.subscriptionservice.mapper;

import org.springframework.stereotype.Component;
import org.unibuc.subscriptionservice.dto.SubscriptionCreateRequestDTO;
import org.unibuc.subscriptionservice.dto.SubscriptionDTO;
import org.unibuc.subscriptionservice.entities.Subscription;
import org.unibuc.subscriptionservice.entities.SubscriptionType;

import java.time.LocalDate;

@Component
public class SubscriptionMapper {
    public Subscription toEntity(SubscriptionCreateRequestDTO dto, String userEmail, Long studioId) {
        Subscription subscription = new Subscription();
        subscription.setUserEmail(userEmail);
        subscription.setStudioId(studioId);
        subscription.setSubscriptionType(dto.getSubscriptionType());
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        return subscription;
    }

    public SubscriptionDTO toDto(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());
        dto.setUserEmail(subscription.getUserEmail());
        dto.setStudioId(subscription.getStudioId());
        dto.setSubscriptionType(subscription.getSubscriptionType());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        return dto;
    }
}
