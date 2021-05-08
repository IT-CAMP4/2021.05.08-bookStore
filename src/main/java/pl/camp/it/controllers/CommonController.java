package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Book;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    Database database;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        List<Book> books = this.database.getAllBooks();
        model.addAttribute("books", books);
        return "main";
    }
}
