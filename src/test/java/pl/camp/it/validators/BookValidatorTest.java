package pl.camp.it.validators;

import org.junit.Assert;
import org.junit.Test;
import pl.camp.it.model.Book;

public class BookValidatorTest {

    @Test
    public void correctValidateBasicsTest() {
        Book book = new Book();
        book.setIsbn("234-234-234-234");
        book.setPieces(5);

        boolean result = BookValidator.validateBasics(book);

        Assert.assertTrue(result);
    }

    @Test
    public void notCorrectValidateBasicsTest() {
        Book book = new Book();
        book.setIsbn("");
        book.setPieces(5);

        boolean result = BookValidator.validateBasics(book);

        Assert.assertFalse(result);
    }

    @Test
    public void correctValidateFullTest() {
        Book book = new Book();
        book.setAuthor("sadfgsdfg");
        book.setTitle("asdfgsdfgsdf");
        book.setPrice(5.0);

        boolean result = BookValidator.validateFull(book);

        Assert.assertTrue(result);
    }

    @Test
    public void notCorrectValidateFullTest() {
        Book book = new Book();
        book.setAuthor("");
        book.setTitle("asdfgsdfgsdf");
        book.setPrice(5.0);

        boolean result = BookValidator.validateFull(book);

        Assert.assertFalse(result);
    }
}
