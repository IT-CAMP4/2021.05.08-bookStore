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

    public class BasketPosition {
        private Book book;
        private int pieces;

        public BasketPosition() {
        }

        public BasketPosition(Book book, int pieces) {
            this.book = book;
            this.pieces = pieces;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public int getPieces() {
            return pieces;
        }

        public void setPieces(int pieces) {
            this.pieces = pieces;
        }
    }
}
