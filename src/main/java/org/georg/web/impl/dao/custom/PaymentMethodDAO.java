package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IPaymentMethodDAO;
import org.georg.web.impl.model.PaymentMethod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentMethodDAO extends GenericDAO<PaymentMethod, Integer> implements IPaymentMethodDAO {

    public PaymentMethodDAO() {
        super(PaymentMethod.class);
    }

    @Override
    public List<PaymentMethod> updateList(List<PaymentMethod> add, List<PaymentMethod> remove) {
        for (PaymentMethod item : add) {
            if (item.getText() != null)
                if (!item.getText().isEmpty())
                    update(item);
        }

        for (PaymentMethod item : remove) {
            delete(item);
        }

        return findAll("id", SortingTypes.asc);
    }
}
