package pl.camp.it.services;

import pl.camp.it.model.Book;

import java.util.List;

public interface IBookService {
    boolean addBook(Book book);
    Book getBookById(int id);
    Book findBookByIsbn(String isbn);
    boolean updateBook(String isbn, Book book);
    List<Book> getBooksWithFilter();
    List<Book> getAllBooks();
}
