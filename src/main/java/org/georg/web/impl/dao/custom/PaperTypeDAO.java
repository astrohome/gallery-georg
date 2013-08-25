package org.georg.web.impl.dao.custom;

import org.georg.web.container.PaperTypeListContainer;
import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IPaperTypeDAO;
import org.georg.web.impl.model.PaperType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
@Repository
public class PaperTypeDAO extends GenericDAO<PaperType, Integer> implements IPaperTypeDAO {
    @Override
    public List<PaperType> updateList(PaperTypeListContainer paperTypeListContainer) {
        ArrayList<PaperType> original = (ArrayList<PaperType>) findAll();
        ArrayList<PaperType> selected = (ArrayList<PaperType>) paperTypeListContainer.getList();

        ArrayList<PaperType> add = new ArrayList(selected);
        add.removeAll(original);
        for (PaperType item : add) {
            if (item.getId() != null && item.getId() != 0) {
                PaperType paperType = getById(item.getId());
                paperType.setPaperType(item.getPaperType());
                update(paperType);
            } else {
                if (item.getPaperType() != null)
                    if (!item.getPaperType().isEmpty())
                        update(item);
            }

        }

        ArrayList<PaperType> remove = new ArrayList(original);
        remove.removeAll(selected);
        for (PaperType item : remove) {
            PaperType paperType = getById(item.getId());
            delete(paperType);
        }

        return findAll("id", SortingTypes.asc);
    }

    public PaperTypeDAO() {
        super(PaperType.class);
    }
}
