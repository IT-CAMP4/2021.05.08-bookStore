package pl.camp.it.dao;

import pl.camp.it.model.Order;

import java.util.List;

public interface IOrderDAO {
    int addOrder(Order order);
    List<Order> getOrdersForUser(int userId);
    void persistOrder(Order order);
    Order getOrderById(int id);
}
