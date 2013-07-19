package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.base.IGalleryDAO;
import org.georg.web.impl.model.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private IGalleryDAO dao;

    @Autowired
    private FileService fileService;

    @Transactional(readOnly = true)
    public List<Gallery> getAll() {
        return dao.findAll("title", IGalleryDAO.SortingTypes.asc);
    }

    @Transactional(readOnly = true)
    public Gallery getByTitle(String title) {
        return dao.findByTitle(title);
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
}
