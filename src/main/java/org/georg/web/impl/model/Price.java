package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 */
@Entity
@Table
@IdClass(IdPK.class)
public class Price implements Serializable {

    @Id
    private Format format;
    @Id
    private PaperType paperType;

    @Column
    @NotNull
    private Float price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "price", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Float getPrice() {
        return price;
    }

    public IdPK getId() {
        return new IdPK(this.format, this.paperType);
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    @Override
    public String toString() {
        return this.format + " - " + this.paperType + " (" + this.price + " грн.)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Price)) return false;

        if (obj == null) return false;

        Price that = (Price) obj;
        if (that.format == null || that.paperType == null
                || this.format == null || this.paperType == null)
            return false;

        return (this.format.equals(that.getFormat()) &&
                this.paperType.equals(that.getPaperType()));
    }

    @Override
    public int hashCode() {
        return 37 * (this.format.hashCode() +
                this.paperType.hashCode() +
                Float.floatToIntBits(price)) + 5;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

