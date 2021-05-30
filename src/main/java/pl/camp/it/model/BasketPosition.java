package pl.camp.it.model;

public class BasketPosition {
    private int id;
    private Book book;
    private int pieces;
    private int bookId;

    public BasketPosition() {
    }

    public BasketPosition(int id, Book book, int pieces) {
        this.id = id;
        this.book = book;
        this.pieces = pieces;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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