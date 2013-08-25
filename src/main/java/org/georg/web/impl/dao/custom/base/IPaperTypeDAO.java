package org.georg.web.impl.dao.custom.base;

import org.georg.web.container.PaperTypeListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.PaperType;

import java.util.List;

/**
 * TODO
 */
public interface IPaperTypeDAO extends IGenericDAO<PaperType, Integer> {
    List<PaperType> updateList(PaperTypeListContainer paperTypeListContainer);
}
