package org.georg.web.impl.model;

import org.georg.web.impl.util.DateUtil;

/**
 * POJO for composite key.
 */
public class DisplayDate implements Comparable {
    private String text;
    private String id;

    public DisplayDate(String dateText, String dateId) {
        this.text = dateText;
        this.id = dateId;
    }

    @Override
    public boolean equals(Object other) {
        return this.getId().equals(((DisplayDate) other).getId());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        return DateUtil.dateIdFromString(id).compareTo(DateUtil.dateIdFromString(((DisplayDate) o).getId()));
    }
}
