package org.georg.web.impl.model;


import com.sun.istack.internal.NotNull;
import org.georg.web.impl.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Entity
@Table
public class Gallery {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gallery_seq")
    @SequenceGenerator(name = "gallery_seq", sequenceName = "gallery_seq")
    @NotNull
    private Long id;
    @NotNull
    @Column(name = "title", columnDefinition = "varchar(255) COLLATE public.\"ru_RU.utf8\"")
    private String title;
    @NotNull
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE NOT NULL")
    private Date created;
    @Transient
    private String createdText;
    @Column
    @NotNull
    private boolean hidden;
    @Column
    @NotNull
    private boolean watermark;
    @Column
    private String password;

    public Gallery() {
    }

    public Gallery(String title, Date created) {
        this.title = title;
        this.created = created;
        this.createdText = DateUtil.toTextString(this.created);
    }

    public String getEncodedTitle() throws UnsupportedEncodingException {
        return URLEncoder.encode(this.title, "UTF-8");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedText() {
        if (createdText == null || createdText.isEmpty()) {
            createdText = DateUtil.toTextString(created);
        }
        return createdText;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isWatermark() {
        return watermark;
    }

    public void setWatermark(boolean watermark) {
        this.watermark = watermark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
