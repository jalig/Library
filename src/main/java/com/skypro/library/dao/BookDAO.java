package com.skypro.library.dao;

import com.skypro.library.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface BookDAO  {
    public void create(Book book);
    public Book getByIsbn(String isbn);
    public List<Book> getAllBooks();
    public void update(Book book);
    public void deleteByIsbn(String isbn);



}
