package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.model.Order;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateOrderDAO implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Deprecated
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public List<Order> getOrdersForUser(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.camp.it.model.Order WHERE user_id = :user_id");
        query.setParameter("user_id", userId);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }

    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.camp.it.model.Order WHERE id = :id");
        query.setParameter("id", id);
        Order order = null;
        try {
            order = query.getSingleResult();
        } catch (NoResultException e) {

        } finally {
            session.close();
        }

        return order;
    }
}
