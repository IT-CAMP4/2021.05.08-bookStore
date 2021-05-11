package pl.camp.it.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.camp.it.model.Book;
import pl.camp.it.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

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

        users.add(new User("Mateusz", "Bereda", "admin", DigestUtils.md5Hex("admin"), User.Role.ADMIN));
        users.add(new User("Mateusz", "Bereda", "user", DigestUtils.md5Hex("user"), User.Role.USER));
    }

    public List<Book> getAllBooks() {
        return this.books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book findBookByIsbn(String isbn) {
        for(Book book : this.books) {
            if(book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public User authenticate(String login, String password) {
        for(User user : this.users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(DigestUtils.md5Hex(password))) {
                return user;
            }
        }

        return null;
    }
}
