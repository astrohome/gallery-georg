package org.georg.web.impl.service.base;

import org.georg.web.impl.dao.base.IGenericDAO;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 */
public abstract class BaseService<T, I extends Serializable> {

    public abstract T getById(I id);

    public abstract Long getCount();

    public abstract List<T> getAll();

    public abstract List<T> getAll(String column, IGenericDAO.SortingTypes sort);

    public abstract T updateItem(T item);
}
