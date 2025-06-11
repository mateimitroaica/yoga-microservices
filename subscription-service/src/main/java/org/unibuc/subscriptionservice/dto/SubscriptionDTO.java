package org.unibuc.subscriptionservice.dto;

import org.unibuc.subscriptionservice.entities.SubscriptionType;

import java.time.LocalDate;

public class SubscriptionDTO {
    private Long id;
    private String userEmail;
    private Long studioId;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionType subscriptionType;

    public SubscriptionDTO(){}

    public SubscriptionDTO(Long id, String userEmail, Long studioId, LocalDate startDate, LocalDate endDate, SubscriptionType subscriptionType) {
        this.id = id;
        this.userEmail = userEmail;
        this.studioId = studioId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subscriptionType = subscriptionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
