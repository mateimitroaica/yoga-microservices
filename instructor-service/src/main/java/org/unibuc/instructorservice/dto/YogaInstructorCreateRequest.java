package org.unibuc.instructorservice.dto;

import org.unibuc.instructorservice.domain.Gender;

public class YogaInstructorCreateRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;

    public YogaInstructorCreateRequest() {}

    public YogaInstructorCreateRequest(String firstName, String lastName, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
