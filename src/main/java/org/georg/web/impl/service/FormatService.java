package org.georg.web.impl.service;

import org.georg.web.container.FormatListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IFormatDAO;
import org.georg.web.impl.model.Format;
import org.georg.web.impl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 */
@Service
public class FormatService extends BaseService<Format, FormatListContainer> {
    @Autowired
    private IFormatDAO formatDao;

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
    public void updateItem(Format item) {
        formatDao.update(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Format> getAll() {
        return formatDao.findAll("id", IGenericDAO.SortingTypes.asc);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Format> updateFromContainer(FormatListContainer formatListContainer) {
        return formatDao.updateList(formatListContainer);
    }
}
