package org.georg.web.impl.model;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table
public class OrderItem {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    private Integer id;

    @Column
    private String image;

    @Column
    private String gallery;

    @ManyToOne(optional = false)
    private Format format;

    @ManyToOne(optional = false)
    private PaperType paperType;

    @Column
    private Integer quantity;

    public OrderItem(String image, String gallery, Format format, PaperType paperType, Integer quantity) {
        this.image = image;
        this.gallery = gallery;
        this.format = format;
        this.paperType = paperType;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
