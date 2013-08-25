package org.georg.web.impl.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IdPK implements Serializable {
    @ManyToOne(optional = false)
    private Format format;

    @ManyToOne(optional = false)
    private PaperType paperType;

    public IdPK() {
    }

    public IdPK(Format format, PaperType paperType) {
        this.format = format;
        this.paperType = paperType;
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
}
