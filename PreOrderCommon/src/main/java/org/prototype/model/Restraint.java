package org.prototype.model;

import java.util.List;

/**
 * Created by Evgeny on 29.11.2015.
 */
public class Restraint {
    private Long id;
    private String name;
    private String address;
    private List<ProductsCatalog> menu;

    public Restraint(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductsCatalog> getMenu() {
        return menu;
    }

    public void setMenu(List<ProductsCatalog> menu) {
        this.menu = menu;
    }
}