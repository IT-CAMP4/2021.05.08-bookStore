package pl.camp.it.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class OrderTest {

    @Test
    public void orderConstructorTest() {
        User user = new User();
        Set<OrderPosition> orderPositions = generateOrderPositionSet();
        double expectedPrice = 352.00;

        Order order = new Order(user, orderPositions);

        Assert.assertEquals(expectedPrice, order.getPrice(), 0.001);
    }

    private Set<OrderPosition> generateOrderPositionSet() {
        Set<OrderPosition> result = new HashSet<>();

        Book book1 = new Book();
        book1.setPrice(50.00);

        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setBook(book1);
        orderPosition1.setPieces(2);

        Book book2 = new Book();
        book2.setPrice(30.00);

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setBook(book2);
        orderPosition2.setPieces(3);

        Book book3 = new Book();
        book3.setPrice(40.50);

        OrderPosition orderPosition3 = new OrderPosition();
        orderPosition3.setBook(book3);
        orderPosition3.setPieces(4);

        result.add(orderPosition1);
        result.add(orderPosition2);
        result.add(orderPosition3);

        return result;
    }
}
