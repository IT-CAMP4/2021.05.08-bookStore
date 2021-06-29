package pl.camp.it.model.rest;

import java.util.Date;
import java.util.Set;

public class Order {
    private int id;
    private String user;
    private Set<String> positions;
    private double price;
    private Date date;

    public Order() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<String> getPositions() {
        return positions;
    }

    public void setPositions(Set<String> positions) {
        this.positions = positions;
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
