package ru.silent_boy.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.silent_boy.spring.models.Book;
import ru.silent_boy.spring.models.Person;
import ru.silent_boy.spring.repositories.BooksRepository;
import ru.silent_boy.spring.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Person> findOwnerById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.map(Book::getOwner);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void setOwner(int bookId, int personId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        Person owner = peopleRepository.findById(personId).orElse(null);
        if (book != null) {
            book.setOwner(owner);
        }
    }

    @Transactional
    public void unsetOwner(int bookId) {
        booksRepository.findById(bookId).ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
