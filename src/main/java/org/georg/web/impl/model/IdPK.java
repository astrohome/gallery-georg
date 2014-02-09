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

    @Override
    public boolean equals(Object obj) {
        //check for self-comparison
        if (this == obj) return true;

        //use instanceof instead of getClass here for two reasons
        //1. if need be, it can match any supertype, and not just one class;
        //2. it renders an explict check for "that == null" redundant, since
        //it does the check for null already - "null instanceof [type]" always
        //returns false. (See Effective Java by Joshua Bloch.)

        if (!(obj instanceof IdPK)) return false;
        //Alternative to the above line :
        //if ( aThat == null || aThat.getClass() != this.getClass() ) return false;

        //cast to native object is now safe
        IdPK that = (IdPK) obj;

        return this.format.getId().equals(that.format.getId()) &&
                this.paperType.getId().equals(that.paperType.getId());
    }

    public IdPK(Format format, PaperType paperType) {
        this.format = format;
        this.paperType = paperType;
    }

    @Override
    public int hashCode() {
        return 37 * (this.getFormat().hashCode() + this.getPaperType().hashCode()) + 5;
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
