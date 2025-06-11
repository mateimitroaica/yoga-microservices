package org.unibuc.yogaclassservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class YogaClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime timeAndDate;

    private double price;

    // Direct enum for filtering/searching in this service
    @Enumerated(EnumType.STRING)
    private YogaClassType yogaStyleType;

    // Foreign resource IDs (via Feign/API, not JPA)
    private Long studioId;
    private Long instructorId;


    // Default constructor
    public YogaClass() {}

    // Full constructor (used by mapper or tests)
    public YogaClass(Long id, String name, LocalDateTime timeAndDate, double price,
                     YogaClassType yogaStyleType,
                     Long studioId, Long instructorId) {
        this.id = id;
        this.name = name;
        this.timeAndDate = timeAndDate;
        this.price = price;
        this.yogaStyleType = yogaStyleType;
        this.studioId = studioId;
        this.instructorId = instructorId;
    }

    // Getters and Setters (keep IDE-generated or use Lombok for brevity)
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
}
