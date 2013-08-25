package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Paper types for buyers
 */
@Entity
@Table
public class PaperType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paper_seq")
    @SequenceGenerator(name = "paper_seq", sequenceName = "paper_seq_id")
    @NotNull
    private Integer id;
    @Column
    @NotNull
    private String paperType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }
}
