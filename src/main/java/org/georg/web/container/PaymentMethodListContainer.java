package org.georg.web.container;

import org.georg.web.container.base.BaseContainer;
import org.georg.web.impl.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodListContainer implements BaseContainer<PaymentMethod> {
    private List<PaymentMethod> paymentMethodList = new ArrayList<>();

    public PaymentMethodListContainer() {
    }

    public PaymentMethodListContainer(List<PaymentMethod> paymentMethods) {
        this.paymentMethodList = paymentMethods;
    }

    @Override
    public List<PaymentMethod> getList() {
        return paymentMethodList;
    }

    @Override
    public void setList(List<PaymentMethod> container) {
        this.paymentMethodList = container;
    }
}
