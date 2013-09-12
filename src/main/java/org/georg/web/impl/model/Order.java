package org.georg.web.impl.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 */
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private Integer id;

    @ManyToOne
    private User user;
    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL) */
    private Set<OrderItem> items = new HashSet<>();

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
}
