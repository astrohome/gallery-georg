package org.georg.web.impl.model;

import javax.persistence.*;

@Entity
@Table
public class PaymentMethod {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
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
