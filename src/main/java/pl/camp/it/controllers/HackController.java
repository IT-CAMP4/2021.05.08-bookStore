package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.impl.BookDAO;
import pl.camp.it.model.Book;

@Controller
public class HackController {

    @Autowired
    IBookDAO bookDAO;

    @RequestMapping(value = "/hack/addBooks", method = RequestMethod.GET)
    public String addBooks() {
        bookDAO.addBook(new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2"));

        bookDAO.addBook(new Book(
                2,
                "Algorytmy sztucznej inteligencji. Ilustrowany przewodnik",
                "Rishal Hurbans",
                47.40,
                5,
                "978-83-283-7507-9"
        ));

        bookDAO.addBook(new Book(
                3,
                "Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                48.30,
                5,
                "978-83-283-0234-1"
        ));

        bookDAO.addBook(new Book(
                4,
                "JavaScript. Przewodnik. Poznaj język mistrzów programowania. Wydanie VII",
                "David Flanagan",
                83.30,
                5,
                "978-83-283-7308-2"
        ));

        return "redirect:/main";
    }
}
