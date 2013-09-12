package org.georg.web.impl.service;

import org.georg.web.container.PaperTypeListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IPaperTypeDAO;
import org.georg.web.impl.model.PaperType;
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
public class PaperTypeService extends BaseContainerService<PaperType, PaperTypeListContainer, Integer> {

    @Override
    @Transactional(readOnly = true)
    public PaperType getById(Integer id) {
        return paperTypeDAO.getById(id);
    }

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
    public PaperType updateItem(PaperType item) {
        return paperTypeDAO.update(item);
    }

    @Override
    @Transactional(readOnly = false)
    public List<PaperType> updateFromContainer(PaperTypeListContainer paperTypeListContainer) {
        List<PaperType> original = paperTypeDAO.findAll();
        List<PaperType> selected = paperTypeListContainer.getList();

        List<PaperType> add = new ArrayList<>(selected);
        add.removeAll(original);

        List<PaperType> addOrUpdate = new ArrayList<>();

        for (PaperType paperType : add) {

            PaperType update;

            if (paperType.getId() != null && paperTypeDAO.getById(paperType.getId()) != null) {
                update = paperTypeDAO.getById(paperType.getId());
            } else {
                update = new PaperType();
            }

            update.setPaperType(paperType.getPaperType());
            addOrUpdate.add(update);
        }

        original.removeAll(selected);

        return paperTypeDAO.updateList(addOrUpdate, original);
    }
}
