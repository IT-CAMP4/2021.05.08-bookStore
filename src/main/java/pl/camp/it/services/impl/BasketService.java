package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.model.OrderPosition;
import pl.camp.it.model.Book;
import pl.camp.it.model.Order;
import pl.camp.it.services.IBasketService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class BasketService implements IBasketService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    public void addBookToBasket(String isbn) {
        Book book = this.bookDAO.findBookByIsbn(isbn);
        if(book != null) {
            this.sessionObject.getBasket().addBook(book);
        }
    }

    public double calculateBasketSum() {
        double sum = 0;
        for(OrderPosition basketPosition : this.sessionObject.getBasket().getBasketPositions()) {
            sum = sum + (basketPosition.getBook().getPrice() * basketPosition.getPieces());
        }

        return sum;
    }

    public void removeBookFromBasket(String isbn) {
        Iterator<OrderPosition> iterator =  this.sessionObject.getBasket().getBasketPositions().iterator();

        while (iterator.hasNext()) {
            if(iterator.next().getBook().getIsbn().equals(isbn)) {
                iterator.remove();
                break;
            }
        }
    }

    public void confirmOrder() {
        List<Book> booksFromDb = this.bookDAO.getAllBooks();

        for(Book book : booksFromDb) {
            Iterator<OrderPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();
            while (iterator.hasNext()) {
                OrderPosition actualBasketPosition = iterator.next();
                if(book.getIsbn().equals(actualBasketPosition.getBook().getIsbn()) && book.getPieces() < actualBasketPosition.getPieces()) {
                    iterator.remove();
                    return;
                }
            }
        }

        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getBasket().getBasketPositions()));
        this.orderDAO.persistOrder(order);

        for(Book book : booksFromDb) {
            for(OrderPosition position : this.sessionObject.getBasket().getBasketPositions()) {
                if(book.getIsbn().equals(position.getBook().getIsbn())) {
                    book.setPieces(book.getPieces() - position.getPieces());
                    this.bookDAO.updateBook(book);
                }
            }
        }

        this.sessionObject.createNewBasket();
    }
}
