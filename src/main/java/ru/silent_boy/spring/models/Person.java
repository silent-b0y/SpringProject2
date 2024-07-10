package ru.silent_boy.spring.models;

import javax.validation.constraints.*;

public class Person {
    private int personId;

    @NotEmpty(message = "Full name can not be empty!")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters!")
    @Pattern(regexp = "[А-Я][а-яё]+ [А-Я][а-яё]+ [А-Я][а-яё]+", message = "Full name should be in this format: Владимир Владимирович Путин")
    private String fullName;

    @Min(value = 1900, message = "Year of birth must be greater or equal 1900")
    @Max(value = 2024, message = "Year of birth must be less or equal 2024")
    private int yearOfBirth;

    public Person(int personId, String fullName, int yearOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {};

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
