package pl.camp.it.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.configuration.TestConfiguration;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.Book;
import pl.camp.it.model.OrderPosition;
import pl.camp.it.model.User;
import pl.camp.it.services.IBasketService;
import pl.camp.it.services.IBookService;
import pl.camp.it.session.SessionObject;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class BasketServiceTest {

    @Autowired
    IBasketService basketService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void addBookToBasketTest() {
        String isbn = "978-83-283-7139-2";

        Mockito.when(this.bookDAO.findBookByIsbn(Mockito.anyString())).thenReturn(generateTestBook());

        this.basketService.addBookToBasket(isbn);

        Assert.assertEquals(1, this.sessionObject.getBasket().getBasketPositions().size());
    }

    @Test
    public void addNotExistingBookToBasketTest() {
        String isbn = "978-83-283-7139-2";

        Mockito.when(this.bookDAO.findBookByIsbn(Mockito.anyString())).thenReturn(null);

        Assert.assertEquals(0, this.sessionObject.getBasket().getBasketPositions().size());
    }

    @Test
    public void calculateBasketSumTest() {
        generateBasket();
        double expectedSum = 190.0;

        double sum = this.basketService.calculateBasketSum();

        Assert.assertEquals(expectedSum, sum, 0.001);
    }

    @Test
    public void removeBookFromBasketTest() {
        generateBasket();
        String isbn = "978-83-283-7139-2";

        this.basketService.removeBookFromBasket(isbn);

        Assert.assertEquals(2, this.sessionObject.getBasket().getBasketPositions().size());

        for(OrderPosition orderPosition : this.sessionObject.getBasket().getBasketPositions()) {
            if(orderPosition.getBook().getIsbn().equals(isbn)) {
                Assert.fail();
            }
        }
    }

    @Test
    public void confirmOrderTest() {
        generateTestBasket2();
        generateTestUser();

        Mockito.when(this.bookDAO.getAllBooks()).thenReturn(generateBooks());

        this.basketService.confirmOrder();

        Mockito.verify(this.bookDAO, Mockito.times(1)).updateBook(Mockito.any());
        Mockito.verify(this.orderDAO, Mockito.times(1)).persistOrder(Mockito.any());
        Assert.assertEquals(0, this.sessionObject.getBasket().getBasketPositions().size());
    }

    private Book generateTestBook() {
         return new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2");
    }

    private void generateBasket() {
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setBook(new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                50.00,
                10,
                "978-83-283-7139-1"));
        orderPosition1.setPieces(2);

        this.sessionObject.getBasket().getBasketPositions().add(orderPosition1);

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setBook(new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                20.00,
                10,
                "978-83-283-7139-2"));
        orderPosition2.setPieces(3);

        this.sessionObject.getBasket().getBasketPositions().add(orderPosition2);

        OrderPosition orderPosition3 = new OrderPosition();
        orderPosition3.setBook(new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                30.00,
                10,
                "978-83-283-7139-3"));
        orderPosition3.setPieces(1);

        this.sessionObject.getBasket().getBasketPositions().add(orderPosition3);
    }

    private List<Book> generateBooks() {
        List<Book> books = new ArrayList<>();

        books.add(new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2"));

        books.add(new Book(
                2,
                "Algorytmy sztucznej inteligencji. Ilustrowany przewodnik",
                "Rishal Hurbans",
                47.40,
                5,
                "978-83-283-7507-9"
        ));

        books.add(new Book(
                3,
                "Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                48.30,
                5,
                "978-83-283-0234-1"
        ));

        books.add(new Book(
                4,
                "JavaScript. Przewodnik. Poznaj język mistrzów programowania. Wydanie VII",
                "David Flanagan",
                83.30,
                5,
                "978-83-283-7308-2"
        ));

        return books;
    }

    private void generateTestBasket2() {
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setBook(new Book(
                4,
                "JavaScript. Przewodnik. Poznaj język mistrzów programowania. Wydanie VII",
                "David Flanagan",
                83.30,
                5,
                "978-83-283-7308-2"
        ));
        orderPosition.setPieces(2);

        this.sessionObject.getBasket().getBasketPositions().add(orderPosition);
    }

    private void generateTestUser() {
        User user = new User();
        user.setLogin("mateusz");
        user.setRole(User.Role.USER);
        user.setId(1);
        user.setName("Mateusz");
        user.setSurname("Bereda");
        user.setPassword("617f41f48d1d4f9c787aed6b0ebc6f7d");

        this.sessionObject.setUser(user);
    }
}
