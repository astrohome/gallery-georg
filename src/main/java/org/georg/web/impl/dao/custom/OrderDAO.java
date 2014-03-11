package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderDAO;
import org.georg.web.impl.model.Order;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by felix on 2/15/14.
 */
@Repository
public class OrderDAO extends GenericDAO<Order, Integer> implements IOrderDAO {

    public OrderDAO() {
        super(Order.class);
    }

    @Override
    public List<Order> getByUser(String id) {
        Criteria criteria = getCurrentSession().createCriteria(Order.class)
                .createCriteria("user", "user")
                .add(Restrictions.eq("id", id));
        return criteria.list();
    }
}

