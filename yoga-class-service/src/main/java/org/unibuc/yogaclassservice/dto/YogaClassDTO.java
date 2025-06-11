package org.unibuc.yogaclassservice.dto;

import org.unibuc.yogaclassservice.entity.YogaClassType;

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

    public String getInstructorFullName() {
        return instructorFullName;
    }

    public void setInstructorFullName(String instructorFullName) {
        this.instructorFullName = instructorFullName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public YogaClassType getYogaStyleType() {
        return yogaStyleType;
    }

    public void setYogaStyleType(YogaClassType yogaStyleType) {
        this.yogaStyleType = yogaStyleType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
