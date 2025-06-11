package org.unibuc.yogaclassservice.exception;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(String message) {
        super("Instructor not found with name: " + message);    }
}
