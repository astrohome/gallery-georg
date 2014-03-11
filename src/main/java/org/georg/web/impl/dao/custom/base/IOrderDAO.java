package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Order;

import java.util.List;

/**
 * Created by felix on 2/13/14.
 */
public interface IOrderDAO extends IGenericDAO<Order, Integer> {
    List<Order> getByUser(String id);
}
