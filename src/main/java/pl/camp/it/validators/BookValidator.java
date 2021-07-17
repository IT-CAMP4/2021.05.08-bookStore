package pl.camp.it.validators;

import pl.camp.it.model.Book;

public class BookValidator {
    public static boolean validateBasics(Book book) {
        if(book.getIsbn() == null || book.getIsbn().equals("") || book.getPieces() == null || book.getPieces() < 0) {
            return false;
        }
        return true;
    }

    public static boolean validateFull(Book book) {
        if(book.getTitle() == null || book.getTitle().equals("")
                || book.getAuthor() == null || book.getAuthor().equals("")
                || book.getPrice() == null || book.getPrice() < 0) {
            return false;
        }
        return true;
    }
}
