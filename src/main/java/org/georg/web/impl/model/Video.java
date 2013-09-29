package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Video {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "video_seq", sequenceName = "video_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_seq")
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
