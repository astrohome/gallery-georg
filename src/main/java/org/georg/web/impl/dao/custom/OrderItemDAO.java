package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderItemDAO;
import org.georg.web.impl.model.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * TODO
 */
@Repository
public class OrderItemDAO extends GenericDAO<OrderItem, Integer> implements IOrderItemDAO {
    public OrderItemDAO() {
        super(OrderItem.class);
    }
}
