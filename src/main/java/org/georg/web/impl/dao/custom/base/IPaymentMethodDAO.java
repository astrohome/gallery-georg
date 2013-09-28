package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.PaymentMethod;

import java.util.List;

/**
 * TODO
 */
public interface IPaymentMethodDAO extends IGenericDAO<PaymentMethod, Integer> {
    List<PaymentMethod> updateList(List<PaymentMethod> add, List<PaymentMethod> remove);
}

