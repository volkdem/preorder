package com.preorder.preorder.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny on 05.12.2015.
 */
public class ProductBin implements Serializable {
    private Long restraintId;
    private Map<Product, Integer> products = new HashMap<>();


    public ProductBin(Long restraintId) {
        this.restraintId = restraintId;
    }


    public void addProduct(Product product, int count) {
        products.put(product, count);

    }

    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal( 0 ).setScale( 2 );
        for( Product product: products.keySet() ) {
            cost = cost.add( product.getCost().multiply( new BigDecimal( products.get( product ) ) ) );
        }

        return cost;
    }

    public boolean containsProduct( Product product ) {
        return  products.containsKey( product );
    }


    public void removeProduct(Product product) {
        products.remove( product );

    }


    public void clear() {
        products.clear();
    }



    public Map<Product, Integer> getProducts() {
        return products;
    }


}
