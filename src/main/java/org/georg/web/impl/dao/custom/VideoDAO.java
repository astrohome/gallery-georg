package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IVideoDAO;
import org.georg.web.impl.model.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoDAO extends GenericDAO<Video, Integer> implements IVideoDAO {
    public VideoDAO() {
        super(Video.class);
    }

    @Override
    public List<Video> updateList(List<Video> add, List<Video> remove) {
        for (Video item : add) {
            if (item.getVideoId() != null)
                update(item);
        }

        for (Video item : remove) {
            delete(item);
        }

        return findAll("id", SortingTypes.asc);
    }
}

