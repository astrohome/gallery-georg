package org.georg.web.impl.service;

import org.georg.web.container.FormatListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IFormatDAO;
import org.georg.web.impl.model.Format;
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
public class FormatService extends BaseContainerService<Format, FormatListContainer, Integer> {
    @Autowired
    private IFormatDAO formatDao;

    @Override
    @Transactional(readOnly = false)
    public void deleteItem(Integer id) {
        Format obj = formatDao.getById(id);
        formatDao.delete(obj);
    }

    @Override
    @Transactional(readOnly = true)
    public Format getById(Integer id) {
        return formatDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return formatDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Format> getAll(String column, IGenericDAO.SortingTypes sort) {
        return formatDao.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public Format updateItem(Format item) {
        return formatDao.update(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Format> getAll() {
        return formatDao.findAll("id", IGenericDAO.SortingTypes.asc);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Format> updateFromContainer(FormatListContainer formatListContainer) {
        List<Format> original = formatDao.findAll();
        List<Format> selected = formatListContainer.getList();

        List<Format> add = new ArrayList<>(selected);
        add.removeAll(original);

        List<Format> addOrUpdate = new ArrayList<>();

        for (Format format : add) {

            Format update;

            if (format.getId() != null && formatDao.getById(format.getId()) != null) {
                update = formatDao.getById(format.getId());
            } else {
                update = new Format();
            }

            update.setFormat(format.getFormat());
            addOrUpdate.add(update);
        }

        original.removeAll(selected);

        return formatDao.updateList(addOrUpdate, original);
    }
}
