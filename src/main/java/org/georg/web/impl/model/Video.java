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

    @Override
    public boolean equals(Object obj) {
        //check for self-comparison
        if (this == obj) return true;

        if (!(obj instanceof Video)) return false;

        Video that = (Video) obj;

        if (that.id == null || this.id == null) return false;

        return this.id.equals(that.id);
    }

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
