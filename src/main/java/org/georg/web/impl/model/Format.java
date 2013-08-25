package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "format", cascade = CascadeType.ALL)
    private Set<Price> price = new HashSet();

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

    @Override
    public String toString() {
        return this.format;
    }

    public Set<Price> getPrice() {
        return price;
    }

    public void setPrice(Set<Price> price) {
        this.price = price;
    }
}
