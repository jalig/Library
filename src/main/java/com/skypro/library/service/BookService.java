package com.skypro.library.service;

import com.skypro.library.model.Book;

import java.util.List;

public interface BookService {
    public void create(Book book);
    public Book getByIsbn(String isbn);
    public List<Book> getAllBooks();
    public void update(Book book);
    public void deleteByIsbn(String isbn);

}