package org.georg.web.impl.service;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Order;
import org.georg.web.impl.service.base.BaseService;

import java.util.List;

/**
 * Created by felix on 2/13/14.
 */
public class OrderService extends BaseService<Order, Integer> {


    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public Long getCount() {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getAll(String column, IGenericDAO.SortingTypes sort) {
        return null;
    }

    @Override
    public Order updateItem(Order item) {
        return null;
    }

    @Override
    public void deleteItem(Integer id) {

    }
}
