package org.georg.web.impl.dao.custom;

import org.georg.web.container.FormatListContainer;
import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IFormatDAO;
import org.georg.web.impl.model.Format;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
@Repository
public class FormatDAO extends GenericDAO<Format, Integer> implements IFormatDAO {

    @Override
    public List<Format> updateList(FormatListContainer formatListContainer) {
        ArrayList<Format> original = (ArrayList<Format>) findAll();
        ArrayList<Format> selected = (ArrayList<Format>) formatListContainer.getList();

        ArrayList<Format> add = new ArrayList(selected);
        add.removeAll(original);
        for (Format item : add) {
            if (item.getId() != null && item.getId() != 0) {
                Format format = getById(item.getId());
                format.setFormat(item.getFormat());
                update(format);
            } else {
                if (item.getFormat() != null)
                    if (!item.getFormat().isEmpty())
                        update(item);
            }

        }

        ArrayList<Format> remove = new ArrayList(original);
        remove.removeAll(selected);
        for (Format item : remove) {
            Format format = getById(item.getId());
            delete(format);
        }

        return findAll("id", SortingTypes.asc);
    }

    public FormatDAO() {
        super(Format.class);
    }
}
