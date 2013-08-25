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

    /*@Id
    private IdPK id;*/

    @Column
    @NotNull
    private Float price;

    public Float getPrice() {
        return price;
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
    /*
    public IdPK getId() {
        return id;
    }

    public void setId(IdPK id) {
        this.id = id;
    }                  */
    /*
    public Format getFormat() {
        return id.getFormat();
    }

    public void setFormat(Format format) {
        this.id.setFormat(format);
    }

    public PaperType getPaperType() {
        return id.getPaperType();
    }

    public void setPaperType(PaperType paperType) {
        this.id.setPaperType(paperType);
    }             */
}

