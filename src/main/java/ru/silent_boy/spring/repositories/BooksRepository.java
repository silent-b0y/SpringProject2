package ru.silent_boy.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.silent_boy.spring.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWith(String title);
}
