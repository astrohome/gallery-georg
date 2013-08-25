package org.georg.web.container.base;

import java.util.List;

/**
 * TODO
 */
public interface BaseContainer<T> {
    List<T> getList();

    void setList(List<T> container);
}
