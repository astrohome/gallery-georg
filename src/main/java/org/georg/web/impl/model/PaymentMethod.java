package org.georg.web.impl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class PaymentMethod {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotNull
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
