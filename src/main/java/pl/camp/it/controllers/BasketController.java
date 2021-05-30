package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.services.IBasketService;
import pl.camp.it.services.IOrderService;
import pl.camp.it.services.impl.BasketService;
import pl.camp.it.services.impl.OrderService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addToBasket/{isbn}", method = RequestMethod.GET)
    public String addBookToBasket(@PathVariable String isbn) {
        this.basketService.addBookToBasket(isbn);

        return "redirect:/main";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("basket", this.sessionObject.getBasket());

        model.addAttribute("sum", this.basketService.calculateBasketSum());

        return "basket";
    }

    @RequestMapping(value = "/removeBookFromBasket/{isbn}", method = RequestMethod.GET)
    public String removeFromBasket(@PathVariable String isbn) {
        this.basketService.removeBookFromBasket(isbn);

        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        this.basketService.confirmOrder();

        return "redirect:/basket";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("orders", this.orderService.getOrdersForUser());

        return "orders";
    }
}
