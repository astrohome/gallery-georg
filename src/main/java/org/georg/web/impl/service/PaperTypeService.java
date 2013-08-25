package org.georg.web.impl.service;

import org.georg.web.container.PaperTypeListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IPaperTypeDAO;
import org.georg.web.impl.model.PaperType;
import org.georg.web.impl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 */
@Service
public class PaperTypeService extends BaseService<PaperType, PaperTypeListContainer> {

    @Autowired
    private IPaperTypeDAO paperTypeDAO;

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return paperTypeDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaperType> getAll(String column, IGenericDAO.SortingTypes sort) {
        return paperTypeDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaperType> getAll() {
        return paperTypeDAO.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void updateItem(PaperType item) {
        paperTypeDAO.update(item);
    }

    @Override
    @Transactional(readOnly = false)
    public List<PaperType> updateFromContainer(PaperTypeListContainer paperTypeListContainer) {
        return paperTypeDAO.updateList(paperTypeListContainer);
    }
}
