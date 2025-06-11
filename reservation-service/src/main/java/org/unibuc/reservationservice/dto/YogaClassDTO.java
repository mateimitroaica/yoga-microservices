package org.unibuc.reservationservice.dto;

import org.unibuc.reservationservice.entity.YogaClassType;

import java.time.LocalDateTime;

public class YogaClassDTO {
    private Long id;
    private String name;
    private LocalDateTime timeAndDate;
    private double price;
    private YogaClassType yogaStyleType;

    private Long studioId;
    private Long instructorId;

    private String studioName;
    private String instructorFullName;

    public YogaClassDTO() {}

    public YogaClassDTO(Long id, String name, LocalDateTime timeAndDate, double price, YogaClassType yogaStyleType, Long studioId, Long instructorId, String studioName, String instructorFullName) {
        this.id = id;
        this.name = name;
        this.timeAndDate = timeAndDate;
        this.price = price;
        this.yogaStyleType = yogaStyleType;
        this.studioId = studioId;
        this.instructorId = instructorId;
        this.studioName = studioName;
        this.instructorFullName = instructorFullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public YogaClassType getYogaStyleType() {
        return yogaStyleType;
    }

    public void setYogaStyleType(YogaClassType yogaStyleType) {
        this.yogaStyleType = yogaStyleType;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getInstructorFullName() {
        return instructorFullName;
    }

    public void setInstructorFullName(String instructorFullName) {
        this.instructorFullName = instructorFullName;
    }
}