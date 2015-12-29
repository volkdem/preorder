package org.prototype.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 29.11.2015.
 */
public class ProductsCatalog implements Serializable{
    private Long id;
    private String name;

    private List< Product > products = new ArrayList<Product>();

    public ProductsCatalog(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product ) {
        products.add( product );
    }
}
