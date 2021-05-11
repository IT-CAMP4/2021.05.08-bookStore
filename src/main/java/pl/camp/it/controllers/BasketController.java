package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Book;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addToBasket/{isbn}", method = RequestMethod.GET)
    public String addBookToBasket(@PathVariable String isbn) {
        Book book = database.findBookByIsbn(isbn);
        if(book == null) {
            return "redirect:/main";
        }

        this.sessionObject.getBasket().addBook(book);

        return "redirect:/main";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("basket", this.sessionObject.getBasket());

        return "basket";
    }
}
