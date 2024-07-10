package ru.silent_boy.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.silent_boy.spring.models.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
