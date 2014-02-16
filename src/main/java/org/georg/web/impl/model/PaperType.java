package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Paper types for buyers
 */
@Entity
@Table
public class PaperType {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @NotNull
    private String paperType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paperType", cascade = CascadeType.ALL)
    private Set<Price> price = new HashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    @Override
    public boolean equals(Object obj) {
        //check for self-comparison
        if (this == obj) return true;

        if (!(obj instanceof PaperType)) return false;

        PaperType that = (PaperType) obj;

        if (that.id == null || this.id == null) return false;

        return this.id.equals(that.id);
    }

    @Override
    public String toString() {
        return this.paperType;
    }

    @Override
    public int hashCode() {
        return 37 * ((this.id != null) ? this.id : 1 + this.paperType.hashCode()) + 5;
    }

    public Set<Price> getPrice() {
        return price;
    }

    public void setPrice(Set<Price> price) {
        this.price = price;
    }
}
