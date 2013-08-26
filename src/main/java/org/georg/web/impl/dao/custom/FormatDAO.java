package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IFormatDAO;
import org.georg.web.impl.model.Format;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 */
@Repository
public class FormatDAO extends GenericDAO<Format, Integer> implements IFormatDAO {

    @Override
    public List<Format> updateList(List<Format> add, List<Format> remove) {
        for (Format item : add) {
            if (item.getFormat() != null)
                if (!item.getFormat().isEmpty())
                    update(item);
        }

        for (Format item : remove) {
            delete(item);
        }

        return findAll("id", SortingTypes.asc);
    }

    public FormatDAO() {
        super(Format.class);
    }
}
