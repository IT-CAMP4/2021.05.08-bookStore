package pl.camp.it.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;
import pl.camp.it.model.Order;
import pl.camp.it.model.User;
import pl.camp.it.services.IAuthenticationService;
import pl.camp.it.services.IBookService;
import pl.camp.it.services.IOrderService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestApiController {

    @Autowired
    IBookService bookService;

    @Autowired
    IAuthenticationService authenticationService;

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody User user) {
        boolean authResult = this.authenticationService.authenticate(user.getLogin(), user.getPassword());

        if(authResult) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity authenticateAndReturnOrders(@RequestHeader String login,
                                                      @RequestHeader String password) {
        boolean authResult = this.authenticationService.authenticate(login, password);

        if(authResult) {
            List<Order> orders = this.orderService.getOrdersForUser();
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
