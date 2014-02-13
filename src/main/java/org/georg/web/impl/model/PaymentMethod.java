package org.georg.web.impl.model;

import javax.persistence.*;

@Entity
@Table
public class PaymentMethod {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
