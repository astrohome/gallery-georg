package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.IdPK;
import org.georg.web.impl.model.Price;

import java.util.List;

/**
 * TODO
 */
public interface IPriceDAO extends IGenericDAO<Price, IdPK> {
    List<Price> updateList(List<Price> add, List<Price> remove);
}
