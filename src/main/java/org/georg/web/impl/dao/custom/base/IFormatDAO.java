package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Format;

import java.util.List;

/**
 * Format DAO
 */
public interface IFormatDAO extends IGenericDAO<Format, Integer> {
    /**
     * Updated all formats.
     *
     * @param add    new formats to add.
     * @param remove removed formats. Attention! Should be persisted!
     * @return new list of formats.
     */
    List<Format> updateList(List<Format> add, List<Format> remove);
}
