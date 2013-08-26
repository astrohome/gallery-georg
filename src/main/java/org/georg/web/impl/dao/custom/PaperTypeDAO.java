package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IPaperTypeDAO;
import org.georg.web.impl.model.PaperType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 */
@Repository
public class PaperTypeDAO extends GenericDAO<PaperType, Integer> implements IPaperTypeDAO {
    @Override
    public List<PaperType> updateList(List<PaperType> add, List<PaperType> remove) {

        for (PaperType item : add) {
            if (item.getPaperType() != null)
                if (!item.getPaperType().isEmpty())
                    update(item);
        }

        for (PaperType item : remove) {
            delete(item);
        }

        return findAll("id", SortingTypes.asc);
    }

    public PaperTypeDAO() {
        super(PaperType.class);
    }
}
