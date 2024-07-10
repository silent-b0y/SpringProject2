package ru.silent_boy.spring.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;

    @NotEmpty(message = "Title can not be empty!")
    @Size(min = 2, max = 100, message = "Title must be less than 100 characters!")
    private String title;

    @NotEmpty(message = "Author can not be empty!")
    @Size(min = 2, max = 100, message = "Author name must be less than 100 characters!")
    private String author;

    @Min(value = 0, message = "Year of publication must be greater or equal 0!")
    @Max(value = 2024, message = "Year of publication must be less or equal 2024!")
    private int yearOfPublication;

    public Book(int bookId, String title, String author, int yearOfPublication) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Book() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
