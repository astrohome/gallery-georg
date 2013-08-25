package org.georg.web.impl.dao.custom;

import org.georg.web.container.PriceListContainer;
import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IPriceDAO;
import org.georg.web.impl.model.IdPK;
import org.georg.web.impl.model.Price;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public List<Price> updateList(PriceListContainer priceListContainer) {
        ArrayList<Price> original = (ArrayList<Price>) findAll();
        ArrayList<Price> selected = (ArrayList<Price>) priceListContainer.getList();
        /*
        ArrayList<Price> add = new ArrayList(selected);
        add.removeAll(original);
        for (Price item : add) {
            if (item.getId() != null) {
                Price price = getById(item.getId());
                price.setPrice(item.getPrice());
                update(price);
            } else {
                if (item.getPrice() != null)
                    update(item);
            }

        }

        ArrayList<Price> remove = new ArrayList(original);
        remove.removeAll(selected);
        for (Price item : remove) {
            Price price = getById(item.getId());
            delete(price);
        }                                  */

        return findAll("id", SortingTypes.asc);
    }
}
