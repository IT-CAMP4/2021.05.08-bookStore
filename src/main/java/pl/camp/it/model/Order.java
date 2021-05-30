package pl.camp.it.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<BasketPosition> positions;
    private double price;
    private Date date;
    private int userId;

    public Order(User user, List<BasketPosition> positions) {
        this.user = user;
        this.positions = positions;

        this.price = 0;
        for(BasketPosition basketPosition : this.positions) {
            this.price = this.price + (basketPosition.getBook().getPrice() * basketPosition.getPieces());
        }

        this.date = new Date();
    }

    public Order() {
    }

    public List<BasketPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<BasketPosition> positions) {
        this.positions = positions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
