package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Basket;
import pl.camp.it.model.Book;
import pl.camp.it.model.Order;
import pl.camp.it.session.SessionObject;
import java.util.List;

import javax.annotation.Resource;
import java.util.Iterator;

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

        double sum = 0;
        for(Basket.BasketPosition basketPosition : this.sessionObject.getBasket().getBasketPositions()) {
            sum = sum + (basketPosition.getBook().getPrice() * basketPosition.getPieces());
        }

        model.addAttribute("sum", sum);

        return "basket";
    }

    @RequestMapping(value = "/removeBookFromBasket/{isbn}", method = RequestMethod.GET)
    public String removeFromBasket(@PathVariable String isbn) {
        Iterator<Basket.BasketPosition> iterator =  this.sessionObject.getBasket().getBasketPositions().iterator();

        while (iterator.hasNext()) {
            if(iterator.next().getBook().getIsbn().equals(isbn)) {
                iterator.remove();
                break;
            }
        }

        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        List<Book> booksFromDb = this.database.getAllBooks();

        for(Book book : booksFromDb) {
            Iterator<Basket.BasketPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();
            while (iterator.hasNext()) {
                Basket.BasketPosition actualBasketPosition = iterator.next();
                if(book.getIsbn().equals(actualBasketPosition.getBook().getIsbn()) && book.getPieces() < actualBasketPosition.getPieces()) {
                    iterator.remove();
                    return "redirect:/basket";
                }
            }
        }

        this.database.addOrder(new Order(this.sessionObject.getUser(), this.sessionObject.getBasket()));

        for(Book book : booksFromDb) {
            for(Basket.BasketPosition position : this.sessionObject.getBasket().getBasketPositions()) {
                if(book.getIsbn().equals(position.getBook().getIsbn())) {
                    book.setPieces(book.getPieces() - position.getPieces());
                }
            }
        }

        this.sessionObject.createNewBasket();

        return "redirect:/basket";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("orders", this.database.getOrdersForUser(this.sessionObject.getUser()));

        return "orders";
    }
}
