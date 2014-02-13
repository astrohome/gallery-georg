package org.georg.web.impl.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotEmpty
    private String image;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;

    @ManyToOne
    @JoinColumn(name = "paperType_id")
    private PaperType paperType;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    @NotEmpty
    private Integer quantity;

    public OrderItem(String image, Gallery gallery, Format format, PaperType paperType, Integer quantity) {
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

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
