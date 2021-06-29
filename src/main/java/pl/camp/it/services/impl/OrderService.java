package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.model.OrderPosition;
import pl.camp.it.model.Book;
import pl.camp.it.model.Order;
import pl.camp.it.services.IOrderService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    public List<Order> getOrdersForUser() {
        return this.orderDAO.getOrdersForUser(this.sessionObject.getUser().getId());
    }

    @Override
    public Order getOrderById(int id) {
        return this.orderDAO.getOrderById(id);
    }
}
