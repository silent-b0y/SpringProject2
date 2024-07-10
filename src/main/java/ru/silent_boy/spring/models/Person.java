package ru.silent_boy.spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Full name can not be empty!")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters!")
    @Pattern(regexp = "[А-Я][а-яё]+ [А-Я][а-яё]+ [А-Я][а-яё]+", message = "Full name should be in this format: Владимир Владимирович Путин")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Year of birth must be greater or equal 1900")
    @Max(value = 2024, message = "Year of birth must be less or equal 2024")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
