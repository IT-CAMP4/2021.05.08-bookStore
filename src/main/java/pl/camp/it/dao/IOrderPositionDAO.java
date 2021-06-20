package pl.camp.it.dao;

import pl.camp.it.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition position, int orderId);
    List<OrderPosition> getOrderPositionsForOrder(int orderId);
}
