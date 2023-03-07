package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skypro.library.exception.BookException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public Book getByIsbn(String isbn) {
        Book book = bookDAO.getByIsbn(isbn);

        if (book == null) {
            throw new BookException("Book with isbn= " + isbn + "doesn't exist");
        }

        return book;
    }

    @Override
    @Transactional
    public void create(Book book) {

        validateBook(book);

        bookDAO.create(book);
    }

    @Override
    @Transactional
    public void update(Book book) {

        Book updatedBook = this.bookDAO.getByIsbn(book.getIsbn());
        if(book == null) {
            throw new BookException("Book is not exist");
        }

        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setYear(book.getYear());

        validateBook(updatedBook);

        bookDAO.update(updatedBook);
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {

        Book book = bookDAO.getByIsbn(isbn);

        if (book == null) {
            throw new BookException("Book with isbn= " + isbn + "doesn't exist");
        }

        bookDAO.deleteByIsbn(isbn);
    }

      private boolean validateBook(Book book) {

        if (book.getTitle() == null || book.getAuthor() == null
                || book.getIsbn() == null || book.getYear() < 0) {
            throw new BookException("Not all fields of the book are filled in");
        }

        String currentIsbn = book.getIsbn();
        String cleandedIsbn = currentIsbn.replaceAll("[\\-\\s]", "");

        if (cleandedIsbn.length() != 13 && !cleandedIsbn.matches("[0-9]+")) {
            throw new BookException("Invalid ISBN");
        }

        int sum = 0;
        for (int i = 0; i < cleandedIsbn.length(); i++) {
            int digit = Character.getNumericValue(cleandedIsbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = 10 - (sum % 10);

        return checkDigit == Character.getNumericValue(cleandedIsbn.charAt(cleandedIsbn.length()-1));
    }

}