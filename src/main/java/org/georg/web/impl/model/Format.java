package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Format table for buying photos
 */
@Entity
@Table
public class Format {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "format_seq", sequenceName = "format_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "format_seq")
    private Integer id;
    @Column
    @NotNull
    private String format;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.id == ((Format) obj).getId())/*
                && (this.format.equals(((Format)obj).getFormat()))*/;
    }
}
