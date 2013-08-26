package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.Price;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class PriceListContainer implements BaseContainer<Price> {
    private List<Price> priceList = new ArrayList<>();

    public PriceListContainer() {
    }

    public PriceListContainer(List<Price> list) {
        this.priceList = list;
    }

    @Override
    public List<Price> getList() {
        return priceList;
    }

    @Override
    public void setList(List<Price> container) {
        this.priceList = container;
    }
}
