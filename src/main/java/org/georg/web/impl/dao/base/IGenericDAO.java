package org.georg.web.impl.dao.base;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable> {
    public enum SortingTypes {
        asc,
        desc
    }

    /**
     * Get an object of type T by id
     *
     * @param id
     * @return An object of type T matching with the given id
     */
    T getById(ID id);


    /**
     * Load an object of type T by id. The same as {@link IGenericDAO#getById(java.io.Serializable)}
     * but uses {@link org.hibernate.Session#load(Class, java.io.Serializable)} method.
     *
     * @param id
     * @return An object of type T matching with the given id
     */
    T loadById(ID id);


    /**
     * Returns all the objects of type T
     *
     * @return A list of all the objects of type T
     */
    List<T> findAll();

    /**
     * Save an object of type T
     *
     * @param entity
     * @return The persisted object given in argument
     */
    T makePersistent(T entity);

    /**
     * Update an object of type T
     *
     * @param entity
     * @return Object
     */
    T update(T entity);

    /**
     * Delete an object of type T
     *
     * @param entity
     * @return Object
     */
    T delete(T entity);

    /**
     * @return The number of DB rows matching to the DAO
     */
    Long count();

    /**
     * Returns all the objects of type T with a sort asc/desc on a column
     *
     * @return A list of all the objects of type T
     */
    List<T> findAll(String sortColumn, SortingTypes order);
}
