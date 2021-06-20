package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBookDAO {

    @Autowired
    Connection connection;

    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result.add(mapBook(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public List<Book> getFilteredBooks(String pattern) {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE title LIKE ? OR author LIKE ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, "%"+pattern+"%");
            preparedStatement.setString(2, "%"+pattern+"%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result.add(mapBook(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public void addBook(Book book) {
        try {
            String sql = "INSERT INTO tbook (title, author, price, pieces, isbn) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getPieces());
            preparedStatement.setString(5, book.getIsbn());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Book findBookByIsbn(String isbn) {
        try {
            String sql = "SELECT * FROM tbook WHERE isbn = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, isbn);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()) {
                return null;
            }

            return mapBook(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void updateBook(Book book) {
        try {
            String sql = "UPDATE tbook SET title = ?, author = ?, price = ?, pieces = ?, isbn = ? WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getPieces());
            preparedStatement.setString(5, book.getIsbn());
            preparedStatement.setInt(6, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Book getBookById(int bookId) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, bookId);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()) {
                return null;
            }

            return mapBook(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private Book mapBook(ResultSet rs) {
        try {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getDouble("price"));
            book.setPieces(rs.getInt("pieces"));
            book.setIsbn(rs.getString("isbn"));

            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
