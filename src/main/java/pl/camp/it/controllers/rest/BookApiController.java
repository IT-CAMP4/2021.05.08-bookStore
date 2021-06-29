package pl.camp.it.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class BookApiController {

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Book> getAllBooks(@RequestParam String author, @RequestParam String title) {
        if(author == null && title == null) {
            //Pobiera z bazy wszystkie książki i je zwraca
            return new ArrayList<>();
        } else {
            //Pobiera z bazy książki pasujące do filtrów i je zwraca
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable int id) {
        return this.bookService.getBookById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        //Zapisuje ksiażkę w bazie
        return new Book();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book, @PathVariable int id) {
        //Updatuje książkę o podanym id
        return new Book();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id) {
        //Usuwa książkę o podanym id
    }
}
