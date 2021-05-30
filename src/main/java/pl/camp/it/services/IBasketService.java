package pl.camp.it.services;

public interface IBasketService {
    void addBookToBasket(String isbn);
    double calculateBasketSum();
    void removeBookFromBasket(String isbn);
    void confirmOrder();
}
