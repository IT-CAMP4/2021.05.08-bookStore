package pl.camp.it.dao;

import pl.camp.it.model.BasketPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(BasketPosition position, int orderId);
    List<BasketPosition> getOrderPositionsForOrder(int orderId);
}
