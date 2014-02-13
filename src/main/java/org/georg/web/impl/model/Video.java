package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Video {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @NotNull
    private String videoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
