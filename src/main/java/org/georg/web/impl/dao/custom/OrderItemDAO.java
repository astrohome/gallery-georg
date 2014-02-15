package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderItemDAO;
import org.georg.web.impl.model.OrderItem;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 */
@Repository
public class OrderItemDAO extends GenericDAO<OrderItem, Integer> implements IOrderItemDAO {
    public OrderItemDAO() {
        super(OrderItem.class);
    }

    @Override
    public List<OrderItem> findAllConfirmedItems(String userId) {
        Criteria criteria = getCurrentSession().createCriteria(OrderItem.class);
        criteria.createCriteria("user", "user").add(Restrictions.eq("id", userId));
        return criteria.list();
    }
}
