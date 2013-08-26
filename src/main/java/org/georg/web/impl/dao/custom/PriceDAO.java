package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IPriceDAO;
import org.georg.web.impl.model.IdPK;
import org.georg.web.impl.model.Price;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 */
@Repository
public class PriceDAO extends GenericDAO<Price, IdPK> implements IPriceDAO {

    public PriceDAO() {
        super(Price.class);
    }

    @Override
    public List<Price> updateList(List<Price> add, List<Price> remove) {

        for (Price item : add) {
            if (item.getPrice() != null)
                update(item);
        }

        for (Price item : remove) {
            delete(item);
        }

        return findAll("id", SortingTypes.asc);
    }
}
