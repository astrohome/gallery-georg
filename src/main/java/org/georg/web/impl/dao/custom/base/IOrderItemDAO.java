package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.OrderItem;

import java.util.List;

/**
 * TODO
 */
public interface IOrderItemDAO extends IGenericDAO<OrderItem, Integer> {
    public List<OrderItem> findAllConfirmedItems(String userId);
}

