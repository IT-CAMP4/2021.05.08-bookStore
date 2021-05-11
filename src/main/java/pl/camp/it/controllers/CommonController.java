package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Book;
import pl.camp.it.model.view.Mail;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        List<Book> books = this.database.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main2(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("mail", new Mail());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        return "contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String processForm(@ModelAttribute Mail mail) {
        System.out.println(mail.getTitle());
        System.out.println(mail.getMessage());
        System.out.println(mail.getName());
        return "redirect:/";
    }
}
