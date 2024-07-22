package ru.silent_boy.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Title can not be empty!")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters!")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author can not be empty!")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters!")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Year of publication must be greater or equal 0!")
    @Max(value = 2024, message = "Year of publication must be less or equal 2024!")
    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "owned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ownedAt;

    @Transient
    private boolean expired;

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getOwnedAt() {
        return ownedAt;
    }

    public void setOwnedAt(Date ownedAt) {
        this.ownedAt = ownedAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
