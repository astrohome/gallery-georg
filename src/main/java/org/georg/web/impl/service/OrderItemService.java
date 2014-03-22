package org.georg.web.impl.service;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IOrderItemDAO;
import org.georg.web.impl.model.Order;
import org.georg.web.impl.model.OrderItem;
import org.georg.web.impl.model.User;
import org.georg.web.impl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 */
@Service
public class OrderItemService extends BaseService<OrderItem, Integer> {
    @Autowired
    private IOrderItemDAO orderItemDAO;

    @Override
    @Transactional(readOnly = false)
    public void deleteItem(Integer id) {
        OrderItem obj = orderItemDAO.getById(id);
        orderItemDAO.delete(obj);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItem getById(Integer id) {
        return orderItemDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return orderItemDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getAll() {
        return orderItemDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<OrderItem> findAllConfirmedItems(User user) {
        if (user == null || user.getLogin() == null || user.getLogin().isEmpty())
            return null;
        return orderItemDAO.findAllConfirmedItems(user.getLogin());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getAll(String column, IGenericDAO.SortingTypes sort) {
        return orderItemDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public OrderItem updateItem(OrderItem item) {
        return orderItemDAO.update(item);
    }

    @Transactional(readOnly = false)
    public void updateItemOrderId(List<OrderItem> items, Order order) {
        for (OrderItem item : items) {
            item.setOrder(order);
            this.updateItem(item);
        }
    }
}
