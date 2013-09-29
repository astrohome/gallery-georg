package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class VideoListContainer implements BaseContainer {
    private List<Video> videoList = new ArrayList<>();

    public VideoListContainer() {
    }

    public VideoListContainer(List<Video> list) {
        this.videoList = list;
    }

    @Override
    public List<Video> getList() {
        return videoList;
    }

    @Override
    public void setList(List container) {
        this.videoList = container;
    }
}
