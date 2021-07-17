package pl.camp.it.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.configuration.TestConfiguration;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class BookServiceTest {

    @Autowired
    IBookService bookService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void addEmptyBookTest() {
        Book book = new Book();

        boolean result = bookService.addBook(book);

        Assert.assertFalse(result);
    }

    @Test
    public void addIsbnAndPiecesBookTest() {
        Book book = new Book();
        book.setIsbn("978-83-283-7139-2");
        book.setPieces(4);

        Book bookFormDb = generateBook();

        Mockito.when(this.bookDAO.findBookByIsbn(Mockito.anyString())).thenReturn(bookFormDb);

        boolean result = this.bookService.addBook(book);

        Mockito.verify(this.bookDAO, Mockito.times(1)).updateBook(Mockito.any());
        Assert.assertEquals(14, (int) bookFormDb.getPieces());
        Assert.assertTrue(result);
    }

    @Test
    public void addNewBookTest() {
        Book book = new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2");

        Mockito.when(this.bookDAO.findBookByIsbn(Mockito.anyString())).thenReturn(null);

        boolean result = this.bookService.addBook(book);

        Mockito.verify(this.bookDAO, Mockito.times(1)).addBook(Mockito.any());
        Mockito.verify(this.bookDAO, Mockito.never()).updateBook(Mockito.any());
        Assert.assertTrue(result);
    }

    private Book generateBook() {
        return new Book(
                1,
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2");
    }
}
