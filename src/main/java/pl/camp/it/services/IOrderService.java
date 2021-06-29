package pl.camp.it.services;

import pl.camp.it.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrdersForUser();
    Order getOrderById(int id);
}
