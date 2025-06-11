package org.unibuc.yogaclassservice.exception;

public class StudioNotFoundException extends RuntimeException {
    public StudioNotFoundException(String message) {
        super("Studio not found with name: " + message);
    }
}
