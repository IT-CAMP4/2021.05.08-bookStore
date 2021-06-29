package pl.camp.it.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.model.OrderPosition;
import pl.camp.it.model.rest.Order;
import pl.camp.it.services.IOrderService;

import java.util.HashSet;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable int id) {
        pl.camp.it.model.Order orderFormDb = orderService.getOrderById(id);

        Order response = new Order();

        response.setId(orderFormDb.getId());
        response.setUser("http://localhost:8081/api/user/" + orderFormDb.getUser().getId());
        response.setPositions(new HashSet<>());
        for(OrderPosition orderPosition : orderFormDb.getPositions()) {
            String orderPositionAddress = "http://localhost:8081/api/orderposition/" + orderPosition.getId();
            response.getPositions().add(orderPositionAddress);
        }
        response.setPrice(orderFormDb.getPrice());
        response.setDate(orderFormDb.getDate());

        return response;
    }
}
