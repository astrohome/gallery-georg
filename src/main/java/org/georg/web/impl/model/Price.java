package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

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
    public boolean equals(Object obj) {
        if (!(obj instanceof Price)) return false;

        if (obj == null)
            return false;
        Price t = (Price) obj;
        if (t == null)
            return false;
        return (this.format.equals(t.getFormat()) &&
                this.paperType.equals(t.getPaperType()) &&
                this.price.equals(t.getPrice()));
    }

    @Override
    public int hashCode() {
        return 37 * (this.format.hashCode() +
                this.paperType.hashCode() +
                Float.floatToIntBits(price)) + 5;
    }
}

