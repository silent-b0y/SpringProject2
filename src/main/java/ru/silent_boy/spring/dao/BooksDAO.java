package ru.silent_boy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.silent_boy.spring.models.Book;
import ru.silent_boy.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public Optional<Person> showOwner(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Book ON Person.person_id = Book.person_id WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny();
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublication(), id);
    }

    public void setOwner(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",
                personId, bookId);
    }

    public void unsetOwner(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?",
                bookId);
    }

    public void deleteBook(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", bookId);
    }
}
