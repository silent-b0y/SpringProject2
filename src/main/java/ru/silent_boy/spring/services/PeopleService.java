package ru.silent_boy.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.silent_boy.spring.models.Book;
import ru.silent_boy.spring.models.Person;
import ru.silent_boy.spring.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Optional<Person> findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public List<Book> findBooksById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            List<Book> books = person.get().getBooks();
            for (Book book : books) {
                long difference = (new Date()).getTime() - book.getOwnedAt().getTime();
                difference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                if (difference >= 10) {
                    book.setExpired(true);
                }
            }
            //Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
