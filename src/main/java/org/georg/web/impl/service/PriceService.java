package org.georg.web.impl.service;

import org.georg.web.container.PriceListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IPriceDAO;
import org.georg.web.impl.model.Price;
import org.georg.web.impl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 */
@Service
public class PriceService extends BaseService<Price, PriceListContainer> {
    @Autowired
    private IPriceDAO priceDAO;

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return priceDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Price> getAll() {
        return priceDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Price> getAll(String column, IGenericDAO.SortingTypes sort) {
        return priceDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateItem(Price item) {
        priceDAO.update(item);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Price> updateFromContainer(PriceListContainer container) {
        return priceDAO.updateList(container);
    }
}
