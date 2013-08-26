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
        if (obj == null) return false;

        if (this.getId() == null) return false;

        if (obj instanceof Format) {
            Format comp = (Format) obj;
            if (comp.getId() == null) return false;

            return this.getId().equals(comp.getId());
        } else return false;
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
