package org.unibuc.subscriptionservice.dto;

public class StudioDTO {
    private Long id;
    private String name;
    private String location;
    private String photoPath;
    private String photoUrl;

    public StudioDTO() {}

    public StudioDTO(Long id, String name, String location, String photoPath, String photoUrl) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.photoPath = photoPath;
        this.photoUrl = photoUrl;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
