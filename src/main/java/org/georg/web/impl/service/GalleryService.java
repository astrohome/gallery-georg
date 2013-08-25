package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.base.IGalleryDAO;
import org.georg.web.impl.model.DisplayDate;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class GalleryService {

    @Autowired
    private IGalleryDAO dao;

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

    @Transactional(readOnly = true)
    public Gallery getById(long id) {
        return dao.getById(id);
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
    public void update(Gallery gallery) {
        dao.update(gallery);
    }

    @Transactional(readOnly = true)
    public Gallery getByCode(String code) {
        Gallery list = dao.getByCode(code);
        return list;
    }
}
