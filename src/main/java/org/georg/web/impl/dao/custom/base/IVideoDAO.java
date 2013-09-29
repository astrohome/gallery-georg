package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Video;

import java.util.List;

public interface IVideoDAO extends IGenericDAO<Video, Integer> {
    List<Video> updateList(List<Video> add, List<Video> remove);
}
