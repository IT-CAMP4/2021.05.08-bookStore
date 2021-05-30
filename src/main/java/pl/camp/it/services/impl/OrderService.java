package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.dao.impl.BookDAO;
import pl.camp.it.dao.impl.OrderDAO;
import pl.camp.it.dao.impl.OrderPositionDAO;
import pl.camp.it.model.BasketPosition;
import pl.camp.it.model.Book;
import pl.camp.it.model.Order;
import pl.camp.it.services.IOrderService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderService implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IBookDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    public List<Order> getOrdersForUser() {
        List<Order> orders = this.orderDAO.getOrdersForUser(this.sessionObject.getUser().getId());

        for(Order order : orders) {
            List<BasketPosition> basketPositions = this.orderPositionDAO.getOrderPositionsForOrder(order.getId());
            for(BasketPosition position : basketPositions) {
                Book book = this.bookDAO.getBookById(position.getBookId());
                position.setBook(book);
            }

            order.setPositions(basketPositions);
        }

        return orders;
    }
}
