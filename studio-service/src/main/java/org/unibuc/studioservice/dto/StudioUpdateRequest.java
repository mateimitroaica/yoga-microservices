package org.unibuc.studioservice.dto;

import org.springframework.web.multipart.MultipartFile;

public class StudioUpdateRequest {
    private String name;
    private String location;
    private MultipartFile file;

    public StudioUpdateRequest() {}

    public StudioUpdateRequest(String name, String location, MultipartFile file) {
        this.name = name;
        this.location = location;
        this.file = file;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
