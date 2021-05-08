package pl.camp.it.database;

import org.springframework.stereotype.Component;
import pl.camp.it.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<Book> books = new ArrayList<>();

    public Database() {
        books.add(new Book(
                "Pragmatyczny programista. Od czeladnika do mistrza. Wydanie II",
                "David Thomas, Andrew Hunt",
                53.90,
                10,
                "978-83-283-7139-2"));

        books.add(new Book(
                "Algorytmy sztucznej inteligencji. Ilustrowany przewodnik",
                "Rishal Hurbans",
                47.40,
                5,
                "978-83-283-7507-9"
        ));

        books.add(new Book(
                "Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                48.30,
                5,
                "978-83-283-0234-1"
        ));

        books.add(new Book(
                "JavaScript. Przewodnik. Poznaj język mistrzów programowania. Wydanie VII",
                "David Flanagan",
                83.30,
                5,
                "978-83-283-7308-2"
        ));
    }

    public List<Book> getAllBooks() {
        return this.books;
    }
}
