package com.skypro.library.controller;

import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skypro")
public class RestControllerBook {

    private final BookService bookService;

    public RestControllerBook(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/api/book")
    public Book addBook(@RequestBody Book book) {
        bookService.create(book);
        return book;
    }

    @PutMapping("/api/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.update(book);
        return book;
    }

    @DeleteMapping("/api/book")
    public String deleteBook(@RequestParam String isbn) {
        bookService.deleteByIsbn(isbn);
        return "Book with isbn= " + isbn + " was deleted";
    }

}