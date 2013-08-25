package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.Format;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class FormatListContainer implements BaseContainer<Format> {
    private List<Format> formatList = new ArrayList<>();

    public FormatListContainer() {
    }

    public FormatListContainer(List<Format> list) {
        formatList = list;
    }

    @Override
    public List<Format> getList() {
        return formatList;
    }

    @Override
    public void setList(List<Format> formatList) {
        this.formatList = formatList;
    }
}
