package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.impl.BookDAO;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.BookValidator;
import java.util.List;

import javax.annotation.Resource;

@Component
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    public boolean addBook(Book book) {
        if(!BookValidator.validateBasics(book)) {
            this.sessionObject.setInfo("Nieprawidłowy ISBN lub ilość !!");
            return false;
        }

        Book bookFromDatabase = this.bookDAO.findBookByIsbn(book.getIsbn());
        if(bookFromDatabase != null) {
            bookFromDatabase.setPieces(bookFromDatabase.getPieces() + book.getPieces());
            this.bookDAO.updateBook(bookFromDatabase);
        } else {
            if(!BookValidator.validateFull(book)) {
                this.sessionObject.setInfo("Książka o podanym ISBN nie istnieje, pełne dane wymagane !!");
                return false;
            }
            this.bookDAO.addBook(book);
        }

        return true;
    }

    public Book findBookByIsbn(String isbn) {
        return this.bookDAO.findBookByIsbn(isbn);
    }

    public boolean updateBook(String isbn, Book book) {
        Book bookFromDb = bookDAO.findBookByIsbn(isbn);
        if(bookFromDb == null) {
            return false;
        }

        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setPieces(book.getPieces());
        bookFromDb.setPrice(book.getPrice());
        bookFromDb.setIsbn(book.getIsbn());

        this.bookDAO.updateBook(bookFromDb);

        return true;
    }

    public List<Book> getBooksWithFilter() {
        if(this.sessionObject.getFindPattern() != null && !this.sessionObject.getFindPattern().equals("")) {
            return this.bookDAO.getFilteredBooks(this.sessionObject.getFindPattern());
        } else {
            return this.bookDAO.getAllBooks();
        }
    }
}