package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderDAO;
import org.georg.web.impl.model.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by felix on 2/15/14.
 */
@Repository
public class OrderDAO extends GenericDAO<Order, Integer> implements IOrderDAO {

    public OrderDAO() {
        super(Order.class);
    }
}
