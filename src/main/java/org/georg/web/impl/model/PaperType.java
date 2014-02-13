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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paperType", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

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
        if (obj == null) return false;

        if (this.getId() == null) return false;

        if (obj instanceof PaperType) {
            PaperType comp = (PaperType) obj;
            if (comp.getId() == null) return false;

            return this.id.equals(comp.getId()) &&
                    this.paperType.equals(comp.getPaperType());
        } else return false;
    }

    @Override
    public String toString() {
        return this.paperType;
    }

    @Override
    public int hashCode() {
        return 37 * (this.id + this.paperType.hashCode()) + 5;
    }

    public Set<Price> getPrice() {
        return price;
    }

    public void setPrice(Set<Price> price) {
        this.price = price;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
