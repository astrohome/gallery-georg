package org.georg.web.impl.service;

import org.georg.web.container.PaymentMethodListContainer;
import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.dao.custom.PaymentMethodDAO;
import org.georg.web.impl.model.PaymentMethod;
import org.georg.web.impl.service.base.BaseContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodService extends BaseContainerService<PaymentMethod, PaymentMethodListContainer, Integer> {
    @Override
    @Transactional(readOnly = false)
    public List<PaymentMethod> updateFromContainer(PaymentMethodListContainer paymentMethodListContainer) {
        List<PaymentMethod> original = paymentMethodDAO.findAll();
        List<PaymentMethod> selected = paymentMethodListContainer.getList();

        List<PaymentMethod> add = new ArrayList<>(selected);
        add.removeAll(original);

        List<PaymentMethod> addOrUpdate = new ArrayList<>();

        for (PaymentMethod paymentMethod : add) {

            PaymentMethod update;

            if (paymentMethod.getId() != null && paymentMethodDAO.getById(paymentMethod.getId()) != null) {
                update = paymentMethodDAO.getById(paymentMethod.getId());
            } else {
                update = new PaymentMethod();
            }

            update.setText(paymentMethod.getText());
            addOrUpdate.add(update);
        }

        original.removeAll(selected);

        return paymentMethodDAO.updateList(addOrUpdate, original);
    }

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;

    @Override
    @Transactional(readOnly = true)
    public PaymentMethod getById(Integer id) {
        return paymentMethodDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return paymentMethodDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethod> getAll() {
        return paymentMethodDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethod> getAll(String column, IGenericDAO.SortingTypes sort) {
        return paymentMethodDAO.findAll(column, sort);
    }

    @Override
    @Transactional(readOnly = false)
    public PaymentMethod updateItem(PaymentMethod item) {
        return paymentMethodDAO.update(item);
    }
}
