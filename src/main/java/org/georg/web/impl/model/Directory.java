package org.georg.web.impl.model;

/**
 * Created with IntelliJ IDEA.
 * User: felix
 * Date: 7/13/13
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Directory {
    private String name;
    private Directory parent;

    public Directory(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
