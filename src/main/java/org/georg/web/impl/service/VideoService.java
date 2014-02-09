package org.georg.web.impl.service;

import org.georg.web.container.VideoListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.base.IVideoDAO;
import org.georg.web.impl.model.Video;
import org.georg.web.impl.service.base.BaseContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService extends BaseContainerService<Video, VideoListContainer, Integer> {
    @Autowired
    private IVideoDAO videoDAO;

    @Override
    @Transactional(readOnly = false)
    public Video updateItem(Video item) {
        return videoDAO.update(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Video getById(Integer id) {
        return videoDAO.getById(id);
    }

    @Override
    public void deleteItem(Integer id) {
        Video obj = videoDAO.getById(id);
        videoDAO.delete(obj);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return videoDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> getAll() {
        return videoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> getAll(String column, IGenericDAO.SortingTypes sort) {
        return videoDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Video> updateFromContainer(VideoListContainer container) {
        List<Video> original = videoDAO.findAll();
        List<Video> selected = container.getList();

        List<Video> add = new ArrayList<>(selected);
        add.removeAll(original);

        List<Video> addOrUpdate = new ArrayList<>();

        for (Video video : add) {

            Video update;

            if (video.getId() != null && videoDAO.getById(video.getId()) != null) {
                update = videoDAO.getById(video.getId());
            } else {
                update = new Video();
            }

            update.setVideoId(video.getVideoId());
            addOrUpdate.add(update);
        }

        original.removeAll(selected);

        return videoDAO.updateList(addOrUpdate, original);
    }
}
