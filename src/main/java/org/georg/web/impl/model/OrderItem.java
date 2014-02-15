package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String image;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "format_id"),
            @JoinColumn(name = "paperType_id")
    })
    private Price price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    @NotNull
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(String image, Gallery gallery, Price price, Integer quantity, User user) {
        this.image = image;
        this.gallery = gallery;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
