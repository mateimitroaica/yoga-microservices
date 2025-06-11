package org.unibuc.yogaclassservice.dto;

import org.unibuc.yogaclassservice.entity.YogaClassType;

import java.time.LocalDateTime;

public class YogaClassCreateRequestDTO {
    private String name;
    private LocalDateTime timeAndDate;
    private double price;

    private YogaClassType yogaStyleType;

    private String studioName;
    private String instructorFullName;

    public YogaClassCreateRequestDTO() {}

    public YogaClassCreateRequestDTO(String name, LocalDateTime timeAndDate, double price, YogaClassType yogaStyleType, String studioName, String instructorFullName) {
        this.name = name;
        this.timeAndDate = timeAndDate;
        this.price = price;
        this.yogaStyleType = yogaStyleType;
        this.studioName = studioName;
        this.instructorFullName = instructorFullName;
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
