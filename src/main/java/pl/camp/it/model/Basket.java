package pl.camp.it.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<OrderPosition> basketPositions = new ArrayList<>();

    public Basket() {
    }

    public List<OrderPosition> getBasketPositions() {
        return basketPositions;
    }

    public void setBasketPositions(List<OrderPosition> basketPositions) {
        this.basketPositions = basketPositions;
    }

    public void addBook(Book book) {
        for(OrderPosition basketPosition : this.basketPositions) {
            if(basketPosition.getBook().getIsbn().equals(book.getIsbn())) {
                basketPosition.setPieces(basketPosition.getPieces() + 1);
                return;
            }
        }

        OrderPosition basketPosition = new OrderPosition();
        basketPosition.setBook(book);
        basketPosition.setPieces(1);

        this.basketPositions.add(basketPosition);
    }
}
