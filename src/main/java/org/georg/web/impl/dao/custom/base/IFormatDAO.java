package org.georg.web.impl.dao.custom.base;

import org.georg.web.container.FormatListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Format;

import java.util.List;

/**
 * TODO
 */
public interface IFormatDAO extends IGenericDAO<Format, Integer> {
    List<Format> updateList(FormatListContainer formatListContainer);
}
