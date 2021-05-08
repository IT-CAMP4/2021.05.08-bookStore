package pl.camp.it.model;

public class Book {
    private String title;
    private String author;
    private double price;
    private int pieces;
    private String isbn;

    public Book(String title, String author, double price, int pieces, String isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.pieces = pieces;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
