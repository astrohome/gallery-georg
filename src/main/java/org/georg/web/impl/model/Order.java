package org.georg.web.impl.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Order_")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @NotNull
    private OrderStatus status;

    @NotNull
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date created;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getSumm() {
        float res = 0;
        for (OrderItem item : items) {
            res += item.getPrice().getPrice();
        }
        return res;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

