package org.georg.web.impl.dao.base;

import org.georg.web.impl.model.Gallery;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: felix
 * Date: 7/13/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGalleryDAO {

    Gallery findOne(Long id);

    List<Gallery> findAll();

    void create(Gallery entity);

    Gallery update(Gallery entity);

    void delete(Gallery entity);

    void deleteById(Long entityId);

}
