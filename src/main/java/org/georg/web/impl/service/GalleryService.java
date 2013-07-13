package org.georg.web.impl.service;

import org.georg.web.impl.model.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GalleryService {
    @Autowired
    private org.georg.web.impl.dao.base.IGalleryDAO dao;

    public List<Gallery> getAll() {
        return dao.findAll();
    }
}
