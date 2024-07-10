package ru.silent_boy.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.silent_boy.spring.models.Person;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullName(String fullName);
}
