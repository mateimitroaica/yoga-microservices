package org.unibuc.subscriptionservice.dto;

import org.unibuc.subscriptionservice.entities.SubscriptionType;

public class SubscriptionCreateRequestDTO {
    private String studioName;
    private SubscriptionType subscriptionType;

    public SubscriptionCreateRequestDTO(){}

    public SubscriptionCreateRequestDTO(String studioName, SubscriptionType subscriptionType) {
        this.studioName = studioName;
        this.subscriptionType = subscriptionType;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
