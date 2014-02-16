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

    @Override
    public boolean equals(Object obj) {
        //check for self-comparison
        if (this == obj) return true;

        if (!(obj instanceof PaymentMethod)) return false;

        PaymentMethod that = (PaymentMethod) obj;

        if (that.id == null || this.id == null) return false;

        return this.id.equals(that.id);
    }

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
