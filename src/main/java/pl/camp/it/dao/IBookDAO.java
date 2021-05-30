package pl.camp.it.dao;

import pl.camp.it.model.Book;

import java.util.List;

public interface IBookDAO {
    List<Book> getAllBooks();
    List<Book> getFilteredBooks(String pattern);
    void addBook(Book book);
    Book findBookByIsbn(String isbn);
    void updateBook(Book book);
    Book getBookById(int bookId);
}
