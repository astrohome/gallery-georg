package org.georg.web.impl.service.base;

import org.georg.web.container.base.BaseContainer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 */
@Service
public abstract class BaseContainerService<T, C extends BaseContainer, I extends Serializable> extends BaseService<T, I> {
    public abstract List<T> updateFromContainer(C container);
}
