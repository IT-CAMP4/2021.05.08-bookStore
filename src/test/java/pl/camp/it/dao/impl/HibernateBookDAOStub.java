package pl.camp.it.dao.impl;

import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import java.util.List;

public class HibernateBookDAOStub implements IBookDAO {
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public Book getBookById(int bookId) {
        return null;
    }
}
