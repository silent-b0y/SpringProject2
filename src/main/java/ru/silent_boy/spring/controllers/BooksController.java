package ru.silent_boy.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.PathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.silent_boy.spring.dao.BooksDAO;
import ru.silent_boy.spring.dao.PersonDAO;
import ru.silent_boy.spring.models.Book;
import ru.silent_boy.spring.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PathMatcher mvcPathMatcher, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, @ModelAttribute("person") Person person , Model model) {
        model.addAttribute("book", booksDAO.show(id));
        Optional<Person> owner = booksDAO.showOwner(id);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{bookId}/setOwner")
    public String setOwner(@ModelAttribute("person") Person owner, @PathVariable int bookId) {
        booksDAO.setOwner(bookId, owner.getPersonId());
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{bookId}/unsetOwner")
    public String unsetOwner(@PathVariable int bookId) {
        booksDAO.unsetOwner(bookId);
        return "redirect:/books/" + bookId;
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        booksDAO.deleteBook(bookId);
        return "redirect:/books";
    }
}
