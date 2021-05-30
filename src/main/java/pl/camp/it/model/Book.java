package pl.camp.it.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private Double price;
    private Integer pieces;
    private String isbn;

    public Book() {
    }

    public Book(int id, String title, String author, Double price, Integer pieces, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.pieces = pieces;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
