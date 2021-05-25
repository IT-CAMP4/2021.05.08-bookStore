package pl.camp.it.model;

import java.util.Date;
import java.util.Random;

public class Order {
    private int id;
    private User user;
    private Basket basket;
    private double price;
    private Date date;

    public Order(User user, Basket basket) {
        this.user = user;
        this.basket = basket;

        this.price = 0;
        for(Basket.BasketPosition basketPosition : basket.getBasketPositions()) {
            this.price = this.price + (basketPosition.getBook().getPrice() * basketPosition.getPieces());
        }

        this.date = new Date();

        this.id = new Random().nextInt(900000) + 100000;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
