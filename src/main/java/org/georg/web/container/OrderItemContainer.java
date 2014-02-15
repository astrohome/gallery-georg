package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felix on 2/13/14.
 */
public class OrderItemContainer implements BaseContainer<OrderItem> {
    private List<OrderItem> orderItemList = new ArrayList();

    public OrderItemContainer() {
    }

    public OrderItemContainer(List<OrderItem> list) {
        this.orderItemList = list;
    }

    @Override
    public List<OrderItem> getList() {
        return orderItemList;
    }

    @Override
    public void setList(List<OrderItem> container) {
        this.orderItemList = container;
    }
}
