package org.georg.web.impl.service;

import org.georg.web.container.PriceListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IFormatDAO;
import org.georg.web.impl.dao.custom.base.IPaperTypeDAO;
import org.georg.web.impl.dao.custom.base.IPriceDAO;
import org.georg.web.impl.model.Format;
import org.georg.web.impl.model.IdPK;
import org.georg.web.impl.model.PaperType;
import org.georg.web.impl.model.Price;
import org.georg.web.impl.service.base.BaseContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
@Service
public class PriceService extends BaseContainerService<Price, PriceListContainer, IdPK> {
    @Autowired
    private IPriceDAO priceDAO;

    @Autowired
    private IFormatDAO formatDAO;

    @Autowired
    private IPaperTypeDAO paperTypeDAO;

    @Override
    @Transactional(readOnly = true)
    public void deleteItem(IdPK id) {
        return;
    }

    @Override
    @Transactional(readOnly = true)
    public Price getById(IdPK id) {
        return priceDAO.getById(id);
    }

    @Transactional(readOnly = true)
    public Price getById(Integer formatId, Integer paperTypeId) {
        Format format = formatDAO.getById(formatId);
        PaperType paperType = paperTypeDAO.getById(paperTypeId);
        if (format == null || paperType == null) return null;

        return getById(new IdPK(format, paperType));
    }

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
    public Price updateItem(Price item) {
        return priceDAO.update(item);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Price> updateFromContainer(PriceListContainer container) {
        List<Price> original = priceDAO.findAll();
        List<Price> selected = container.getList();

        List<Price> add = new ArrayList<>(selected);
        add.removeAll(original);

        List<Price> addOrUpdate = new ArrayList<>();

        for (Price price : add) {

            Price update;

            if (priceDAO.getById(new IdPK(price.getFormat(), price.getPaperType())) != null) {
                update = priceDAO.getById(new IdPK(price.getFormat(), price.getPaperType()));
            } else {
                update = new Price();
            }

            update.setFormat(formatDAO.getById(price.getFormat().getId()));
            update.setPaperType(paperTypeDAO.getById(price.getPaperType().getId()));
            update.setPrice(price.getPrice());
            addOrUpdate.add(update);
        }

        original.removeAll(selected);

        return priceDAO.updateList(addOrUpdate, original);
    }
}
