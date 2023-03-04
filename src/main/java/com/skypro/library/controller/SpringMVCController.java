package com.skypro.library.controller;

import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/skypro")
public class SpringMVCController {

    private final BookService bookservice;

    public SpringMVCController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @RequestMapping("/web")
    public String getBooks(Model model, Book book) {
        model.addAttribute("books", bookservice.getAllBooks());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("isbn", book.getIsbn());
        model.addAttribute("year", book.getYear());
        return "dashboard";
    }

}