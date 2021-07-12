package pl.camp.it.dao.impl;

import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.model.Order;

import java.util.List;

public class HibernateOrderDAOStub implements IOrderDAO {
    @Override
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public List<Order> getOrdersForUser(int userId) {
        return null;
    }

    @Override
    public void persistOrder(Order order) {

    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }
}
