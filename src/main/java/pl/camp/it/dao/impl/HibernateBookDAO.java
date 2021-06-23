package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateBookDAO implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.model.Book");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FORM pl.camp.it.model.Book WHERE title like :pattern OR author like :pattern");
        query.setParameter("pattern", "%" + pattern + "%");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.model.Book WHERE isbn = :isbn");
        query.setParameter("isbn", isbn);
        Book book = null;
        try {
            book = query.getSingleResult();
        } catch (NoResultException e) {

        } finally {
            session.close();
        }

        return book;
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.model.Book WHERE id = :id");
        query.setParameter("id", id);
        Book book = null;
        try {
            book = query.getSingleResult();
        } catch (NoResultException e) {

        } finally {
            session.close();
        }

        return book;
    }
}
