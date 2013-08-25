package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.PaperType;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class PaperTypeListContainer implements BaseContainer<PaperType> {

    private List<PaperType> paperTypeList = new ArrayList<>();

    public PaperTypeListContainer() {
    }

    public PaperTypeListContainer(List<PaperType> list) {
        paperTypeList = list;
    }

    @Override
    public List<PaperType> getList() {
        return paperTypeList;
    }

    @Override
    public void setList(List<PaperType> paperTypeList) {
        this.paperTypeList = paperTypeList;
    }
}
