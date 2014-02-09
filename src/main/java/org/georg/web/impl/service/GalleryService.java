package org.georg.web.impl.service;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IGalleryDAO;
import org.georg.web.impl.model.DisplayDate;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.service.base.BaseService;
import org.georg.web.impl.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class GalleryService extends BaseService<Gallery, Long> {

    @Autowired
    private IGalleryDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return dao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gallery> getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gallery> getAll(String column, IGenericDAO.SortingTypes sort) {
        return null;
    }

    @Autowired
    private FileService fileService;

    @Transactional(readOnly = true)
    public SortedMap<DisplayDate, List<Gallery>> getAllByDate(boolean includeHidden) {
        List<Gallery> list = dao.findAll("created", IGalleryDAO.SortingTypes.asc);

        SortedMap<DisplayDate, List<Gallery>> result = new TreeMap<>();
        for (final Gallery gallery : list) {
            if (gallery.isHidden() && !includeHidden) continue;

            String dateText = DateUtil.toTextString(gallery.getCreated());
            String dateId = DateUtil.toIdString(gallery.getCreated());

            DisplayDate dd = new DisplayDate(dateText, dateId);

            if (result.containsKey(dd)) {
                if (result.get(dd) != null) {
                    result.get(dd).add(gallery);
                }
            } else {
                result.put(dd, new ArrayList<Gallery>() {{
                    add(gallery);
                }});
            }
        }

        return result;
    }

    @Transactional(readOnly = true)
    public Gallery getByTitle(String title) {
        return dao.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public Gallery getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteItem(Long id) {
        Gallery obj = dao.getById(id);
        dao.delete(obj);
    }

    @Transactional(readOnly = true)
    public Gallery getByTitleFromDBorFileSystem(String title) {
        Gallery obj = dao.findByTitle(title);
        if (obj == null) {
            obj = fileService.getDirectoryByTitle(title);
        }

        return obj;
    }

    @Transactional(readOnly = false)
    public Gallery updateItem(Gallery gallery) {
        Gallery updatedGallery = dao.update(gallery);
        return updatedGallery;
    }

    @Transactional(readOnly = true)
    public Gallery getByCode(String code) {
        Gallery list = dao.getByCode(code);
        return list;
    }
}
