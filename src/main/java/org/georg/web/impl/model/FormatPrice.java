package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TODO
 */
@Entity
@Table
public class FormatPrice {
    @Id
    private IdPK id;

    @Column
    @NotNull
    private float price;

    @Embeddable
    private class IdPK implements Serializable {
        @ManyToOne
        private Format item;

        @ManyToOne
        private PaperType product;

        public Format getItem() {
            return item;
        }

        public void setItem(Format item) {
            this.item = item;
        }

        public PaperType getProduct() {
            return product;
        }

        public void setProduct(PaperType product) {
            this.product = product;
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public IdPK getId() {
        return id;
    }

    public void setId(IdPK id) {
        this.id = id;
    }
}
