package cl.luci.stock.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Generic JPA functionalities.
 * @author Oreste Luci
 */
public abstract class AbstractJpaDAO<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(T entity) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
    }

    public T findById(Long id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        T t = (T)session.get(clazz,id);
        tx.commit();
        session.close();
        return t;
    }

    public List<T> findAll() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        List<T> l = session.createQuery("from " + clazz.getName()).list();
        tx.commit();
        session.close();
        return l;
    }

    public void delete(T entity) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    public void deleteById(Long entityId) {
        T entity = findById(entityId);
        delete(entity);
    }

    public void update(T entity) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }
}