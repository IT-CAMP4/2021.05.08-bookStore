package pl.camp.it.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketPosition> basketPositions = new ArrayList<>();

    public Basket() {
    }

    public List<BasketPosition> getBasketPositions() {
        return basketPositions;
    }

    public void setBasketPositions(List<BasketPosition> basketPositions) {
        this.basketPositions = basketPositions;
    }

    public void addBook(Book book) {
        for(BasketPosition basketPosition : this.basketPositions) {
            if(basketPosition.getBook().getIsbn().equals(book.getIsbn())) {
                basketPosition.setPieces(basketPosition.getPieces() + 1);
                return;
            }
        }

        BasketPosition basketPosition = new BasketPosition();
        basketPosition.setBook(book);
        basketPosition.setPieces(1);

        this.basketPositions.add(basketPosition);
    }


}
