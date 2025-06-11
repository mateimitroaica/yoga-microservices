package org.unibuc.yogaclassservice.exception;

public class YogaClassNotFoundException extends RuntimeException {
    public YogaClassNotFoundException(Long id) {
        super("Yoga class not found with ID: " + id);
    }
}
