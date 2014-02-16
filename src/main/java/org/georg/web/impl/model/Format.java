package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Format table for buying photos
 */
@Entity
@Table
public class Format {
    @Id
    @GeneratedValue
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
        //check for self-comparison
        if (this == obj) return true;

        if (!(obj instanceof Format)) return false;

        Format that = (Format) obj;

        if (that.id == null || this.id == null) return false;

        return this.id.equals(that.id);
    }

    @Override
    public String toString() {
        return this.format;
    }

    @Override
    public int hashCode() {
        return 37 * ((this.id != null) ? this.id : 1 + this.format.hashCode()) + 5;
    }

    public Set<Price> getPrice() {
        return price;
    }

    public void setPrice(Set<Price> price) {
        this.price = price;
    }
}
