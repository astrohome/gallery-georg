package org.georg.web.impl.service;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderDAO;
import org.georg.web.impl.model.Order;
import org.georg.web.impl.model.OrderItem;
import org.georg.web.impl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * Created by felix on 2/13/14.
 */
@Service
public class OrderService extends BaseService<Order, Integer> {

    @Autowired
    private IOrderDAO orderDAO;

    @Override
    @Transactional(readOnly = true)
    public Order getById(Integer id) {
        return orderDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return orderDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll(String column, IGenericDAO.SortingTypes sort) {
        return orderDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public Order updateItem(Order item) {
        return orderDAO.update(item);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteItem(Integer id) {
        Order item = orderDAO.getById(id);
        orderDAO.delete(item);
    }

    @Transactional(readOnly = false)
    public Order composeOrder(List<OrderItem> items) {
        Order item = new Order();
        item.setItems(new HashSet<>(items));
        item.setUser(items.get(0).getUser());
        return orderDAO.update(item);
    }
}
