package org.georg.web.impl.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


/**
 * Implementation of {@link org.georg.web.base.IGenericDAO}; basic class for DAO-related work.
 *
 * @param <T>  type of object.
 * @param <ID> primary key.
 */
public class GenericDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

    private final Class<T> persistentClass;

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    public GenericDAO(Class<T> clazz) {
        this.persistentClass = clazz;
    }

    @Override
    final public T loadById(ID id) {
        return (T) getCurrentSession().load(persistentClass, id);
    }

    @Override
    final public T getById(ID id) {
        return (T) getCurrentSession().get(persistentClass, id);
    }

    @Override
    final public List<T> findAll() {
        return getCurrentSession().createCriteria(persistentClass).list();
    }

    @Override
    final public List<T> findAll(String sortColumn, SortingTypes order) {
        if (order.asc.equals(order)) {
            return getCurrentSession().createCriteria(persistentClass).addOrder(Order.asc(sortColumn)).list();
        } else {
            return getCurrentSession().createCriteria(persistentClass).addOrder(Order.desc(sortColumn)).list();
        }
    }

    @Override
    final public Long count() {
        return (Long) getCurrentSession().createCriteria(persistentClass).setProjection(Projections.rowCount()).list().get(0);
    }

    @Override
    final public T makePersistent(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return makePersistent(entity);
    }

    @Override
    final public T delete(T entity) {
        this.getCurrentSession().delete(entity);
        return entity;
    }

    final public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    final public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    final public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
