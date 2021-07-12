package pl.camp.it.model;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Assert;
import org.junit.Test;

public class BasketTest {

    @Test
    public void addExistingBookToBasket() {
        Basket basket = generateTestBasket();
        Book book = new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2");
        int expectedBasketPositions = 2;
        int expectedBookPieces = 2;

        basket.addBook(book);

        Assert.assertEquals(expectedBasketPositions, basket.getBasketPositions().size());

        int positionsCounter = 0;
        for(OrderPosition orderPosition : basket.getBasketPositions()) {
            if(orderPosition.getBook().getIsbn().equals(book.getIsbn())) {
                positionsCounter++;
                Assert.assertEquals(expectedBookPieces, orderPosition.getPieces());
            }
        }

        Assert.assertEquals(1, positionsCounter);
    }

    @Test
    public void addNotExistingBookToBasket() {
        Basket basket = generateTestBasket();
        Book book = new Book(
                3456,
                "fdghdfgh",
                "dfghdfgh",
                53.90,
                10,
                "6734567-4567-456");
        int expectedBasketPositions = 3;
        int expectedBookPieces = 1;

        basket.addBook(book);

        Assert.assertEquals(expectedBasketPositions, basket.getBasketPositions().size());

        for(OrderPosition orderPosition : basket.getBasketPositions()) {
            Assert.assertEquals(expectedBookPieces, orderPosition.getPieces());
        }
    }

    private Basket generateTestBasket() {
        Basket basket = new Basket();

        Book book1 = new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2");

        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setId(4565);
        orderPosition1.setPieces(1);
        orderPosition1.setBook(book1);

        basket.getBasketPositions().add(orderPosition1);

        Book book2 = new Book(
                2,
                "Algorytmy sztucznej inteligencji. Ilustrowany przewodnik",
                "Rishal Hurbans",
                47.40,
                5,
                "978-83-283-7507-9"
        );

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setBook(book2);
        orderPosition2.setId(354634);
        orderPosition2.setPieces(1);

        basket.getBasketPositions().add(orderPosition2);

        return basket;
    }
}
