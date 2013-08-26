package org.georg.web.impl.service.base;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 */
@Service
public abstract class BaseService<T, C extends BaseContainer, I extends Serializable> {
    public abstract Long getCount();

    public abstract List<T> getAll();

    public abstract List<T> getAll(String column, IGenericDAO.SortingTypes sort);

    public abstract void updateItem(T item);

    public abstract List<T> updateFromContainer(C container);

    public abstract T getById(I id);
}
