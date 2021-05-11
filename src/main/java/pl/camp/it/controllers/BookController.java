package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Book;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.BookValidator;

import javax.annotation.Resource;

@Controller
public class BookController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        return "addBook";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book) {
        if(!BookValidator.validateBasics(book)) {
            this.sessionObject.setInfo("Nieprawidłowy ISBN lub ilość !!");
            return "redirect:/addBook";
        }
        Book bookFromDatabase = this.database.findBookByIsbn(book.getIsbn());
        if(bookFromDatabase != null) {
            bookFromDatabase.setPieces(bookFromDatabase.getPieces() + book.getPieces());
        } else {
            if(!BookValidator.validateFull(book)) {
                this.sessionObject.setInfo("Książka o podanym ISBN nie istnieje, pełne dane wymagane !!");
                return "redirect:/addBook";
            }
            this.database.addBook(book);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/editBook/{isbn}", method = RequestMethod.GET)
    public String editBookForm(Model model, @PathVariable String isbn) {
        Book book = this.database.findBookByIsbn(isbn);
        if(book == null) {
            return "redirect:/main";
        }
        model.addAttribute("book", book);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        return "editBook";
    }

    @RequestMapping(value = "/editBook/{isbn}", method = RequestMethod.POST)
    public String editBook(@PathVariable String isbn, @ModelAttribute Book book) {
        Book bookFromDb = database.findBookByIsbn(isbn);
        if(bookFromDb == null) {
            return "redirect:/editBook/" + isbn;
        }

        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setPieces(book.getPieces());
        bookFromDb.setPrice(book.getPrice());
        bookFromDb.setIsbn(book.getIsbn());

        return "redirect:/main";
    }
}
