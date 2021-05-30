package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;
import pl.camp.it.services.impl.BookService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BookController {

    @Autowired
    IBookService bookService;

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
        if(this.bookService.addBook(book)) {
            return "redirect:/";
        }
        return "redirect:/addBook";
    }

    @RequestMapping(value = "/editBook/{isbn}", method = RequestMethod.GET)
    public String editBookForm(Model model, @PathVariable String isbn) {
        Book book = this.bookService.findBookByIsbn(isbn);
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
        if(this.bookService.updateBook(isbn, book)) {
            return "redirect:/main";
        }

        return "redirect:/editBook/" + isbn;
    }
}
